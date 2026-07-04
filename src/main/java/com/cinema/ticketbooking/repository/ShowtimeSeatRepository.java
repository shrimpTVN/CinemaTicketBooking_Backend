package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.ShowtimeSeat;
import com.cinema.ticketbooking.entity.ShowtimeSeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ShowtimeSeatRepository extends JpaRepository<ShowtimeSeat, ShowtimeSeatId> {
 
    List<ShowtimeSeat> findByShowtimeId(Integer showtimeId);

    @Query(value="select count(*) from showtime_seat where showtime_id = :showtimeId and status = 'AVAILABLE'", nativeQuery = true)
    Integer countAvailableSeatsByShowtimeId(Integer showtimeId);
    /**
     * 1. STANDARD JPQL QUERY: Fetching the Visual Seat Map
     * We use 'JOIN FETCH' to pull the related Seat entity in a single query,
     * preventing the dreaded N+1 problem when React renders the grid.
     */
    @Query("SELECT ss FROM ShowtimeSeat ss " +
            "JOIN FETCH ss.seat s " +
            "WHERE ss.id.showtimeId = :showtimeId " +
            "ORDER BY s.rowLabel ASC, s.colNumber ASC")
    List<ShowtimeSeat> findSeatMapByShowtimeId(@Param("showtimeId") Integer showtimeId);


    /**
     * 2. MODIFYING QUERY: Bulk Releasing Expired Holds
     * Instead of fetching 1,000 expired objects into Java memory and saving them one by one,
     * we execute a single atomic SQL UPDATE directly in MySQL.
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE ShowtimeSeat ss " +
            "SET ss.status = 'AVAILABLE', ss.holdBy = null, ss.holdUntil = null " +
            "WHERE ss.status = 'HELD' AND ss.holdUntil <= :now")
    int releaseExpiredHolds(@Param("now") Instant now);


    /**
     * 3. NATIVE MYSQL QUERY: Finding Hot/Contiguous Seats (Optional High-Performance Use Case)
     * If you need database-specific optimizations that JPQL doesn't support well.
     */
    @Query(value = "SELECT * FROM showtime_seat " +
            "WHERE showtime_id = :showtimeId AND status = 'AVAILABLE' " +
            "LIMIT :count",
            nativeQuery = true)
    List<ShowtimeSeat> findAvailableSeatsNative(@Param("showtimeId") Integer showtimeId,
                                                @Param("count") int count);


    List<ShowtimeSeat> findByStatus(String held);

    List<ShowtimeSeat> findAllById(Iterable<ShowtimeSeatId> ids);
}