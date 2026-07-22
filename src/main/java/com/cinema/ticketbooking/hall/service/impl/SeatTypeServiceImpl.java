package com.cinema.ticketbooking.hall.service.impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.SeatTypeDto;
import com.cinema.ticketbooking.entity.SeatType;
import com.cinema.ticketbooking.hall.service.ISeatTypeService;
import com.cinema.ticketbooking.repository.SeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatTypeServiceImpl implements ISeatTypeService {
    private final SeatTypeRepository seatTypeRepository;

    @Override
    public List<SeatTypeDto> getAllSeatTypes() {
        List<SeatType> seatTypes = seatTypeRepository.findAll();
        return seatTypes.stream().map(this::transformToDto).toList();

    }

    @Override
    public SeatTypeDto getSeatTypeById(Integer id) {
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + id));
        return transformToDto(seatType);
    }

    @Override
    public SeatTypeDto createSeatType(SeatTypeDto seatTypeDto) {
        SeatType seatType = new SeatType();
        seatType.setName(seatTypeDto.name());
        seatType.setDescription(seatTypeDto.description());
        seatType.setImage(seatTypeDto.image());

        if (seatTypeDto.status() != null) {
            seatType.setStatus(seatTypeDto.status());
        } else seatType.setStatus("ON");
        if (seatTypeDto.priceSurcharge() != null) {
            seatType.setPriceSurcharge(seatTypeDto.priceSurcharge());
        } else seatType.setPriceSurcharge(BigDecimal.ZERO);

        SeatType newSeatType = seatTypeRepository.save(seatType);
        return transformToDto(newSeatType);
    }

    @Override
    public SeatTypeDto updateSeatType(Integer id, SeatTypeDto seatTypeDto) {
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + id));

        BeanUtils.copyProperties(seatTypeDto, seatType);

        SeatType updatedSeatType = seatTypeRepository.save(seatType);
        return transformToDto(updatedSeatType);
    }

    private SeatTypeDto transformToDto(SeatType seatType) {
        return new SeatTypeDto(seatType.getId(), seatType.getName(), seatType.getPriceSurcharge(),
                seatType.getDescription(), seatType.getImage(), seatType.getStatus());
    }
}

