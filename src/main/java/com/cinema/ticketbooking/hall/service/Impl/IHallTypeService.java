package com.cinema.ticketbooking.hall.service.Impl;

import com.cinema.ticketbooking.dto.HallTypeDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IHallTypeService {

    public List<HallTypeDto> getAllHallTypes();
    public HallTypeDto getHallTypeById( Integer id);
    public HallTypeDto getHallTypeByName( String name);

    public HallTypeDto createHallType(HallTypeDto hallTypeDto);
    public HallTypeDto updateHallType(Integer id, HallTypeDto hallTypeDto);
    public String updateHallTypeStatus(Integer id, String status);

}
