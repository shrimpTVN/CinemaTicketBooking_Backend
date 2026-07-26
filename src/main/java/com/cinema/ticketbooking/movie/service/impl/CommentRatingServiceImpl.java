package com.cinema.ticketbooking.movie.service.impl;

import com.cinema.ticketbooking.dto.requestDto.CommentRatingRequestDto;
import com.cinema.ticketbooking.dto.responseDto.CommentRatingResponseDto;
import com.cinema.ticketbooking.entity.CommentRating;
import com.cinema.ticketbooking.entity.Movie;
import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.movie.service.ICommentRatingService;
import com.cinema.ticketbooking.repository.CommentRatingRepository;
import com.cinema.ticketbooking.repository.MovieRepository;
import com.cinema.ticketbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentRatingServiceImpl implements ICommentRatingService {
    private final CommentRatingRepository commentRatingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentRatingResponseDto> getAllCommentRatingsByMovieId(int movieId) {
        List<CommentRating> commentRatings = commentRatingRepository.findByMovieId(movieId);
        return commentRatings.stream().map(this::transformToDto).toList();
    }

    @Override
    public CommentRatingResponseDto getCommentRatingById(int commentRatingId) {
        CommentRating commentRating = commentRatingRepository.findById(commentRatingId)
                .orElseThrow(() -> new RuntimeException("CommentRating not found with id: " + commentRatingId));
        return transformToDto(commentRating);
    }

    @Override
    public CommentRatingResponseDto createCommentRating(CommentRatingRequestDto commentRatingRequestDto) {
//      take the movie that user purchase if not return an invalid argument error
//      user only can comment and rate the movie that they purchased
        Movie movie = takePurchasedMovie(commentRatingRequestDto.movieId(), commentRatingRequestDto.userId());

        User user = userRepository.findById(commentRatingRequestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + commentRatingRequestDto.userId()));

        CommentRating commentRating = new CommentRating();
        commentRating.setMovie(movie);
        commentRating.setUser(user);
        commentRating.setRating(commentRatingRequestDto.rating());
        commentRating.setComment(commentRatingRequestDto.comment());

        CommentRating newCommentRating = commentRatingRepository.save(commentRating);
        return transformToDto(newCommentRating);
    }


    private Movie takePurchasedMovie(Integer movieId, Integer userId) {
        List<Movie> movies = movieRepository.findMoviesPurchasedByUserId(userId);

        return movies.stream()
                .filter(purchasedMovie -> Objects.equals(purchasedMovie.getId(), movieId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User have not purchased this movie with movieId="
                        + movieId + " and user with userId=" + userId));
    }

    @Override
    public CommentRatingResponseDto updateCommentRating(Integer id, CommentRatingRequestDto commentRatingRequestDto) {

        CommentRating commentRating = commentRatingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommentRating not found with id: " + id));
        //check to make sure that the comment rating belongs to the user and movie specified in the request
        if (!Objects.equals(commentRating.getUser().getId(), commentRatingRequestDto.userId()) ||
                !Objects.equals(commentRating.getMovie().getId(), commentRatingRequestDto.movieId())) {
            throw new IllegalArgumentException("Comment rating is not belong to this movie with movieId="
                    + commentRatingRequestDto.movieId() + " and user with userId=" + commentRatingRequestDto.userId());
        }

        commentRating.setRating(commentRatingRequestDto.rating());
        commentRating.setComment(commentRatingRequestDto.comment());

        CommentRating updatedCommentRating = commentRatingRepository.save(commentRating);
        return transformToDto(updatedCommentRating);
    }

    @Override
    public void deleteCommentRating(int commentRatingId, Integer userId) {
        CommentRating commentRating = commentRatingRepository.findById(commentRatingId)
                .orElseThrow(() -> new RuntimeException("CommentRating not found with id: " + commentRatingId));
        //check to make sure that the comment belong to the user
        if (!Objects.equals(commentRating.getUser().getId(), userId)) {
            throw new IllegalArgumentException("Comment rating is not belong to this movie with movieId="
                    + commentRating.getMovie().getId() + " and user with userId=" + commentRating.getUser().getId());
        }

        commentRatingRepository.delete(commentRating);
    }

    private CommentRatingResponseDto transformToDto(CommentRating commentRating) {
        return new CommentRatingResponseDto(
                commentRating.getId(),
                commentRating.getUser().getId(),
                commentRating.getUser().getName(),
                commentRating.getMovie().getId(),
                commentRating.getComment(),
                commentRating.getRating(),
                commentRating.getCreatedAt(),
                commentRating.getUpdateAt()
        );
    }

}
