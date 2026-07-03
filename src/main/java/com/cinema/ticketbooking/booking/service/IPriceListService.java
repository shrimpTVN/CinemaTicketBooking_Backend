package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.requestDto.PriceListRequestDto;
import com.cinema.ticketbooking.dto.responseDto.PriceListResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IPriceListService {

    public List<PriceListResponseDto> getAllPriceLists();
    public PriceListResponseDto getPriceListById(int id);
    public List<PriceListResponseDto> getPriceListByAudienceTypeId(Integer audienceTypeId);
    public PriceListResponseDto getSeatPrice(Integer seatId, Integer audienceTypeId, LocalDate date);

    public PriceListResponseDto createPriceList(PriceListRequestDto priceListRequestDto);
    public PriceListResponseDto updatePriceList(Integer id, PriceListRequestDto priceListRequestDto);
    public void updatePriceListStatus(Integer id, String status);

}
