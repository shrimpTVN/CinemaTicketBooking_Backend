package com.cinema.ticketbooking.media.service.impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.requestDto.EventRequestDto;
import com.cinema.ticketbooking.dto.responseDto.EventResponseDto;
import com.cinema.ticketbooking.entity.Event;
import com.cinema.ticketbooking.media.service.IEventService;
import com.cinema.ticketbooking.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    @Override
    public List<EventResponseDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        return events.stream().map(this::transformToDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public EventResponseDto getEventById(int id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return transformToDto(event);
    }

    @Override
    public EventResponseDto createEvent(EventRequestDto eventDto) {
        Event event = new Event();
        BeanUtils.copyProperties(eventDto, event);
        Event newEvent = eventRepository.save(event);

        return transformToDto(newEvent);
    }

    @Override
    public EventResponseDto updateEvent(Integer id, EventRequestDto eventDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        BeanUtils.copyProperties(eventDto, event);

        Event updatedEvent = eventRepository.save(event);
        return transformToDto(updatedEvent);
    }

    @Override
    public void updateEventStatus(Integer id, String status) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        event.setStatus(status);
        eventRepository.save(event);
    }

    private EventResponseDto transformToDto(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getPoster(),
                event.getBanner(),
                event.getStatus()
        );
    }
}
