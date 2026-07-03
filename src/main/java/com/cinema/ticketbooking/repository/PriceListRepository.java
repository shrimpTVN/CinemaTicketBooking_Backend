package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceListRepository extends JpaRepository<PriceList, Integer> {
    public List<PriceList> findByAudienceTypeId(Integer audienceTypeId);

    @Query(value ="SELECT * FROM price_list " +
            "WHERE hall_type_id = :hallTypeId AND seat_type_id = :seatTypeId AND audience_type_id = :audienceTypeId",
            nativeQuery = true)
    public List<PriceList> findByReferences(@Param("hallTypeId") Integer hallTypeId,
                                            @Param("seatTypeId") Integer seatTypeId,
                                            @Param("audienceTypeId") Integer audienceTypeId);
}