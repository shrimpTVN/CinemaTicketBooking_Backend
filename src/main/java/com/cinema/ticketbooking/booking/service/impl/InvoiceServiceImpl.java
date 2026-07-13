package com.cinema.ticketbooking.booking.service.impl;

import com.cinema.ticketbooking.AudienceTypeRepository;
import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.booking.service.IPriceListService;
import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import com.cinema.ticketbooking.dto.requestDto.InvoiceDetailRequestDto;
import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.requestDto.SeatCheckoutRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceDetailResponseDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeResponseDto;
import com.cinema.ticketbooking.dto.responseDto.TicketResponseDto;
import com.cinema.ticketbooking.entity.*;
import com.cinema.ticketbooking.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;
    private final AudienceTypeRepository audienceTypeRepository;
    private final IPriceListService priceListService;
    private final ProductRepository productRepository;
    private final IShowtimeSeatService showtimeSeatService;

    @Override
    public List<InvoiceResponseDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(this::transformToDto).toList();
    }

    @Override
    public InvoiceResponseDto getInvoiceById(Integer id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found for ID: " + id));
        return transformToDto(invoice);
    }

    @Override
    public List<InvoiceResponseDto> getInvoicesByUserId(Integer userId) {
        List<Invoice> invoices = invoiceRepository.findByUserId(userId);
        return invoices.stream().map(this::transformToDto).toList();
    }
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Showtime showtime = showtimeRepository.findById(request.showtimeId())
                .orElseThrow(() -> new IllegalArgumentException("Showtime not found"));

        // 1. INITIALIZE & SAVE PARENT FIRST (To get the generated ID)
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setStatus("PENDING");
        invoice.setVat(BigDecimal.valueOf(0.08));
        invoice.setPaymentMethod(request.paymentMethod());
        invoice.setTotalAmount(BigDecimal.ZERO); // Temporary, will be recalculated later

        Invoice savedInvoice = invoiceRepository.save(invoice); // ID is now generated!

        // 2. PROCESS CHILDREN
        List<Ticket> tickets = processTickets(savedInvoice, showtime, request.seatCheckouts(), user.getId());
        List<InvoiceDetail> details = processInvoiceDetail(savedInvoice, request.invoiceDetails());

        savedInvoice.getTickets().addAll(tickets);
        savedInvoice.getInvoiceDetails().addAll(details);

        // 3. CALCULATE TOTALS ACCURATELY
        BigDecimal ticketTotal = tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal detailTotal = details.stream()
                .map(InvoiceDetail::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal subTotal = ticketTotal.add(detailTotal);
        BigDecimal vatAmount = subTotal.multiply(savedInvoice.getVat());

        savedInvoice.setTotalAmount(subTotal.add(vatAmount));

        // Hibernate automatically flushes the updated totals and inserts the children
        // because of cascade = CascadeType.ALL on the Invoice entity.
        invoiceRepository.save(savedInvoice);
        return transformToDto(savedInvoice);
    }

    private List<Ticket> processTickets(Invoice invoice, Showtime showtime,
                                        List<SeatCheckoutRequestDto> checkouts, Integer userId) {
        List<Ticket> tickets = new ArrayList<>();

        // BATCH FETCHING: Extract all seat IDs and fetch them in one query
        List<Integer> allSeatIds = checkouts.stream()
                .flatMap(c -> c.seatIds().stream())
                .toList();

        // Ideally: List<Seat> allSeats = seatRepository.findAllById(allSeatIds);
        // Map them by ID for O(1) memory lookup below...
        for (SeatCheckoutRequestDto checkout : checkouts) {
            AudienceType audienceType = audienceTypeRepository.findById(checkout.audienceTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Audience type not found"));

            showtimeSeatService.confirmBooking(showtime.getId(), checkout.seatIds(), userId); // Ensure atomic transition

            for (Integer seatId : checkout.seatIds()) {
                Seat seat = seatRepository.findById(seatId).orElseThrow(); // Replace with batch map lookup

                if (!seat.getHall().getId().equals(showtime.getHall().getId())) {
                    throw new IllegalArgumentException("Seat mismatch for hall.");
                }
                Ticket ticket = new Ticket();
                ticket.setShowtime(showtime);
                ticket.setInvoice(invoice);
                ticket.setAudienceType(audienceType);
                ticket.setSeat(seat);
                ticket.setPrice(priceListService.getSeatPrice(seatId, audienceType.getId(), showtime.getDate()).price());

                tickets.add(ticket);
            }
        }
        return tickets;
    }

    private List<InvoiceDetail> processInvoiceDetail(Invoice invoice, List<InvoiceDetailRequestDto> requests) {
        if (requests == null || requests.isEmpty()) return new ArrayList<>();

        List<InvoiceDetail> details = new ArrayList<>();


        List<Integer> productIds = requests.stream()
                .map(InvoiceDetailRequestDto::productId)
                .collect(Collectors.toList());
        // BATCH FETCHING: Fetch all products in one query
        Map<Integer, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        for (InvoiceDetailRequestDto req : requests) {
            Product product = productMap.get(req.productId());
            if (product == null) throw new IllegalArgumentException("Product not found");

            InvoiceDetail detail = new InvoiceDetail();
            // invoice.getId() is now safe to use!
            detail.setId(new InvoiceDetailId(product.getId(), invoice.getId()));
            detail.setInvoice(invoice);
            detail.setProduct(product);
            detail.setQuantity(req.quantity());
            detail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(req.quantity())));

            details.add(detail);
        }
        return details;
    }



    @Override
    public void updateInvoiceStatus(Integer id, String status) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found for ID: " + id));
        invoice.setStatus(status);
        invoiceRepository.save(invoice);
    }

    private InvoiceResponseDto transformToDto(Invoice invoice) {
        Showtime showtime = invoice.getTickets().stream().findFirst()
                .map(Ticket::getShowtime)
                .orElseThrow(() -> new IllegalArgumentException("No showtime found for invoice ID: " + invoice.getId()));
    return new InvoiceResponseDto(
            invoice.getId(),
            invoice.getUser().getId(),
            transformToDto(showtime),
            invoice.getTickets().stream().map(this::transformToDto).toList(),
            invoice.getInvoiceDetails().stream().map(this::transformToDto).toList(),
            invoice.getPaymentMethod(), invoice.getTotalAmount(), invoice.getVat());
    }

    private TicketResponseDto transformToDto(Ticket ticket) {
        return new TicketResponseDto(ticket.getId(), ticket.getShowtime().getId(), ticket.getAudienceType().getName(),
                ticket.getSeat().getRowLabel(),ticket.getSeat().getColNumber(), ticket.getPrice());
    }

    private InvoiceDetailResponseDto transformToDto(InvoiceDetail invoiceDetail) {
        return new InvoiceDetailResponseDto(invoiceDetail.getProduct().getName(), invoiceDetail.getQuantity(), invoiceDetail.getPrice());
    }

    private ShowtimeResponseDto transformToDto(Showtime showtime) {
        return new ShowtimeResponseDto(showtime.getId(), showtime.getHall().getId(), showtime.getHall().getName(),
                showtime.getMovie().getId(), showtime.getMovie().getTitle(), showtime.getDate(), showtime.getStartTime(), showtime.getType());
    }

//    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
//    public InvoiceResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto) {
//        User user = userRepository.findById(invoiceRequestDto.userId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + invoiceRequestDto.userId()));
//        Showtime showtime = showtimeRepository.findById(invoiceRequestDto.showtimeId())
//                .orElseThrow(() -> new IllegalArgumentException("Showtime not found for ID: " + invoiceRequestDto.showtimeId()));
//
////        set basic information for invoice
//        Invoice invoice = new Invoice();
//        invoice.setUser(user);
//        invoice.setStatus("PENDING");
//        invoice.setVat(BigDecimal.valueOf(0.08));
//        invoice.setPaymentMethod(invoiceRequestDto.paymentMethod());
//
////        process and set tickets
//        Set<Ticket> tickets = new LinkedHashSet<>(processTickets(invoice, showtime, invoiceRequestDto.seatCheckouts()));
//        invoice.setTickets(tickets);
//
////        process and set invoice detail
//        Set<InvoiceDetail> invoiceDetails = new LinkedHashSet<>(processInvoiceDetail(invoice, invoiceRequestDto.invoiceDetails()));
//        invoice.setInvoiceDetails(invoiceDetails);
//
////    calculate totalAmount
//        BigDecimal totalAmount = tickets.stream().map(Ticket::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
//        totalAmount = totalAmount.add(invoiceDetails.stream().map(InvoiceDetail::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        invoice.setTotalAmount(totalAmount.add(totalAmount.multiply(invoice.getVat())));
//
//        Invoice savedInvoice = invoiceRepository.save(invoice);
//
//        return transformToDto(savedInvoice);
//    }
//    private List<Ticket> processTickets(Invoice invoice, Showtime showtime, List<SeatCheckoutRequestDto> seatCheckouts) {
//        List<Ticket> tickets = new ArrayList<>();
//        seatCheckouts.forEach(seatCheckout -> {
//            AudienceType audienceType = audienceTypeRepository.findById(seatCheckout.audienceTypeId())
//                    .orElseThrow(() -> new IllegalArgumentException("Audience type not found for ID: " + seatCheckout.audienceTypeId()));
//
//            seatCheckout.seatIds().forEach(seatId -> {
//                Seat seat = seatRepository.findById(seatId)
//                        .orElseThrow(() -> new IllegalArgumentException("Seat not found for ID: " + seatId));
//
//                if (!seat.getHall().getId().equals(showtime.getHall().getId())) {
//                    throw new IllegalArgumentException("Seat ID: " + seatId + " does not belong to the hall of the showtime.");
//                }
//
//                Ticket ticket = new Ticket();
//                ticket.setShowtime(showtime);
//                ticket.setInvoice(invoice);
//                ticket.setAudienceType(audienceType);
//                ticket.setSeat(seat);
//                ticket.setPrice(priceListService.getSeatPrice(seatId, audienceType.getId(),
//                        showtime.getDate()).price());
//
//                tickets.add(ticket);
//
////                change showtime-seat status to "SOLD"
//                ShowtimeSeat showtimeSeat = showtimeSeatRepository.findByShowtimeIdAndSeatId(showtime.getId(), seat.getId());
//                if (showtimeSeat != null) {
//                    showtimeSeat.setStatus("SOLD");
//                    showtimeSeatRepository.save(showtimeSeat);
//                } else {
//                    throw new RuntimeException("ShowtimeSeat not found for showtime ID: " + showtime.getId() + " and seat ID: " + seat.getId());
//                }
//            });
//
//
//        });
//
//        return tickets;
//    }
//    private List<InvoiceDetail> processInvoiceDetail(Invoice invoice, List<InvoiceDetailRequestDto> invoiceDetailRequests) {
//        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
//
//        invoiceDetailRequests.forEach(invoiceDetailRequest -> {
//            InvoiceDetailId id = new InvoiceDetailId(invoiceDetailRequest.productId(), invoice.getId());
//
//            Product product = productRepository.findById(invoiceDetailRequest.productId())
//                    .orElseThrow(() -> new IllegalArgumentException("Product not found for ID: " + invoiceDetailRequest.productId()));
//
//            InvoiceDetail invoiceDetail = new InvoiceDetail();
//            invoiceDetail.setId(id);
//            invoiceDetail.setInvoice(invoice);
//            invoiceDetail.setProduct(product);
//            invoiceDetail.setQuantity(invoiceDetailRequest.quantity());
//            invoiceDetail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(invoiceDetailRequest.quantity())));
//
//            invoiceDetails.add(invoiceDetail);
//
//        });
//        return invoiceDetails;
//    }
}