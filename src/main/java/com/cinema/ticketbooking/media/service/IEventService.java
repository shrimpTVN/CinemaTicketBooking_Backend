package com.cinema.ticketbooking.media.service;

import com.cinema.ticketbooking.dto.requestDto.EventRequestDto;
import com.cinema.ticketbooking.dto.responseDto.EventResponseDto;


import java.util.List;

public interface IEventService {

    public List<EventResponseDto> getAllEvents();
    public EventResponseDto getEventById(int id);

    public EventResponseDto createEvent(EventRequestDto eventDto);
    public EventResponseDto updateEvent(Integer id, EventRequestDto eventDto);
    public void updateEventStatus(Integer id, String status);
}
