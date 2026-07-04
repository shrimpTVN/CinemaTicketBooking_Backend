package com.cinema.ticketbooking.hall.service.Impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.HallTypeDto;
import com.cinema.ticketbooking.entity.HallType;
import com.cinema.ticketbooking.repository.HallTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallTypeService implements IHallTypeService {
    private final HallTypeRepository hallTypeRepository;

    @Override
    public List<HallTypeDto> getAllHallTypes() {
        List<HallType> hallTypes = hallTypeRepository.findAll();
        return hallTypes.stream().map(this::transformToDto).toList();
    }

    @Override
    public HallTypeDto getHallTypeById(Integer id) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + id));
        return transformToDto(hallType);
    }

    @Override
    public HallTypeDto getHallTypeByName(String name) {
        HallType hallType = hallTypeRepository.findByName(name);
        if (hallType == null) {
            throw new ResourceNotFoundException("Hall type not found with name: " + name);
        }
        return transformToDto(hallType);
    }

    @Override
    public HallTypeDto createHallType(HallTypeDto hallTypeDto) {
        HallType hallType = new HallType();
        hallType.setName(hallTypeDto.name());
        hallType.setDescription(hallTypeDto.description());
        hallType.setConvenience(hallTypeDto.convenience());
        hallType.setStyle(hallTypeDto.style());
        hallType.setImages(hallTypeDto.images());
        hallType.setStatus(hallTypeDto.status() != null ? hallTypeDto.status() : "ON");

        HallType savedHallType = hallTypeRepository.save(hallType);
        return transformToDto(savedHallType);
    }

    @Override
    public HallTypeDto updateHallType(Integer id, HallTypeDto hallTypeDto) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + id));

        if (hallTypeDto.name() != null) {
            hallType.setName(hallTypeDto.name());
        }
        if (hallTypeDto.description() != null) {
            hallType.setDescription(hallTypeDto.description());
        }
        if (hallTypeDto.convenience() != null) {
            hallType.setConvenience(hallTypeDto.convenience());
        }
        if (hallTypeDto.style() != null) {
            hallType.setStyle(hallTypeDto.style());
        }
        if (hallTypeDto.images() != null) {
            hallType.setImages(hallTypeDto.images());
        }

        HallType updatedHallType = hallTypeRepository.save(hallType);
        return transformToDto(updatedHallType);
    }

    @Override
    public String updateHallTypeStatus(Integer id, String status) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + id));
        hallType.setStatus(status);
        hallTypeRepository.save(hallType);
        return "Hall type status updated successfully";
    }

    private HallTypeDto transformToDto(HallType hallType) {
        return new HallTypeDto(hallType.getId(), hallType.getName(), hallType.getDescription(),
                hallType.getConvenience(), hallType.getStyle(), hallType.getImages(), hallType.getStatus());
    }
}
