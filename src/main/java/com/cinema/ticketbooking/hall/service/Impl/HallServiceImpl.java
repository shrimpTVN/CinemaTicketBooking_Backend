package com.cinema.ticketbooking.hall.service.Impl;

import com.cinema.ticketbooking.core.exception.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.HallDto;
import com.cinema.ticketbooking.entity.Hall;
import com.cinema.ticketbooking.hall.service.IHallService;
import com.cinema.ticketbooking.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallServiceImpl  implements IHallService {
    private final HallRepository hallRepository;


    @Override
    public List<HallDto> getAllHalls() {
        List<Hall> halls = hallRepository.findAll();
        return halls.stream().map(this::transformToDto).toList();
    }

    @Override
    public HallDto getHallById(int id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
        return transformToDto(hall);
    }

    @Override
    public HallDto createHall(HallDto hallDto) {
        Hall hall = new Hall();
        hall.setName(hallDto.name());
        hall.setWidth(hallDto.width());
        hall.setHeight(hallDto.height());
        hall.setImages(hallDto.images());
        hall.setDescription(hallDto.description());
        hall.setConvenience(hallDto.convenience());
        hallRepository.save(hall);

        return transformToDto(hall);
    }

    @Override
    public HallDto updateHall(int id, HallDto hallDto) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
        if (hallDto.name() != null) {
            hall.setName(hallDto.name());
        }
        if (hallDto.width() != null) {
            hall.setWidth(hallDto.width());
        }
        if (hallDto.height() != null) {
            hall.setHeight(hallDto.height());
        }
        if (hallDto.images() != null) {
            hall.setImages(hallDto.images());
        }
        if (hallDto.description() != null) {
            hall.setDescription(hallDto.description());
        }
        if (hallDto.convenience() != null) {
            hall.setConvenience(hallDto.convenience());
        }

        if (hallDto.status() != null) {
            hall.setStatus(hallDto.status());
        }

        hallRepository.save(hall);
        return transformToDto(hall);
    }

    private HallDto transformToDto(Hall hall){
        return new HallDto(hall.getId(), hall.getName(), hall.getWidth(),
                hall.getHeight(), hall.getImages(), hall.getDescription(),
                hall.getConvenience(), hall.getStatus());
    }
}
