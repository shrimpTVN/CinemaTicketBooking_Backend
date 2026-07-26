package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.requestDto.CommentRatingRequestDto;
import com.cinema.ticketbooking.dto.responseDto.CommentRatingResponseDto;

import java.util.List;

public interface ICommentRatingService {
    public List<CommentRatingResponseDto> getAllCommentRatingsByMovieId(int movieId);

    public CommentRatingResponseDto getCommentRatingById(int commentRatingId);
    public CommentRatingResponseDto createCommentRating(CommentRatingRequestDto commentRatingRequestDto);
    public CommentRatingResponseDto updateCommentRating(Integer id, CommentRatingRequestDto commentRatingRequestDto);
    public void deleteCommentRating(int commentRatingId, Integer userId);
}
