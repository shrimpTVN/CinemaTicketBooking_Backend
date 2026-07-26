package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Movie;
import com.cinema.ticketbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public List<Movie> findByStatus(String status);

//    @Query("SELECT m " +
//            "FROM User u JOIN FETCH u.invoices i JOIN FETCH i.tickets tk " +
//            "JOIN FETCH tk.showtime st JOIN FETCH st.movie m " +
//            "where u.id = :userId")
//    public List<Movie> findByUserId(@Param("userId") Integer userId);

    /**
     * Retrieves a distinct list of Movies for which a specific user has purchased tickets.
     * Utilizes standard INNER JOINs to traverse the entity graph without unnecessarily
     * loading intermediate entities into the Persistence Context.
     */
    @Query("SELECT DISTINCT m " +
            "FROM User u " +
            "JOIN u.invoices i " +
            "JOIN i.tickets tk " +
            "JOIN tk.showtime st " +
            "JOIN st.movie m " +
            "WHERE u.id = :userId")
    List<Movie> findMoviesPurchasedByUserId(@Param("userId") Integer userId);
}