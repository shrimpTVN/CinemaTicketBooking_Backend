package com.cinema.ticketbooking.hall.service.impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.HallTypeDto;
import com.cinema.ticketbooking.entity.HallType;
import com.cinema.ticketbooking.hall.service.IHallTypeService;
import com.cinema.ticketbooking.repository.HallTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        BeanUtils.copyProperties(hallTypeDto, hallType);

        HallType savedHallType = hallTypeRepository.save(hallType);
        return transformToDto(savedHallType);
    }

    @Override
    public HallTypeDto updateHallType(Integer id, HallTypeDto hallTypeDto) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + id));

        BeanUtils.copyProperties(hallTypeDto, hallType);

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
