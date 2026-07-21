package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    @Query("SELECT s FROM Showtime s WHERE s.date >= :presentDate AND s.movie.id = :movieId")
    public List<Showtime> findByMovieId(@Param("movieId") Integer movieId, @Param("presentDate") LocalDate presentDate);

    @Query("SELECT s FROM Showtime s WHERE s.date >= :presentDate AND s.hall.id = :hallId")
    public List<Showtime> findByHallId(@Param("hallId") Integer hallId, @Param("presentDate") LocalDate presentDate);

    public List<Showtime> findByDate(LocalDate date);

    @Query("SELECT s FROM Showtime s WHERE s.date >= :presentDate  AND s.movie.id = :movieId AND s.date = :date AND s.hall.id = :hallId")
    public List<Showtime> findByMovieIdAndDateAndHallId(@Param("movieId") Integer movieId,
                                                          @Param("date") LocalDate date,
                                                        @Param("presentDate") LocalDate presentDate,
                                                        @Param("hallId") Integer hallId);

    @Query("SELECT s FROM Showtime s WHERE s.date >= :presentDate  AND s.movie.id = :movieId AND s.date = :date ")
    public List<Showtime> findByMovieIdAndDate(@Param("movieId") Integer movieId, @Param("date") LocalDate date, @Param("presentDate") LocalDate presentDate);

}