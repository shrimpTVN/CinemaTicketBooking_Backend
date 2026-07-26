package com.cinema.ticketbooking.movie.controller;

import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import com.cinema.ticketbooking.dto.requestDto.CommentRatingRequestDto;
import com.cinema.ticketbooking.dto.responseDto.CommentRatingResponseDto;
import com.cinema.ticketbooking.movie.service.ICommentRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment-ratings")
@RequiredArgsConstructor
public class CommentRatingController {

    private final ICommentRatingService commentRatingService;

    @GetMapping("/movies/{id}")
    public ResponseEntity<List<CommentRatingResponseDto>> getAllCommentRatingsByMovieId(@PathVariable int id) {
        List<CommentRatingResponseDto> commentRatings = commentRatingService.getAllCommentRatingsByMovieId(id);
        return ResponseEntity.ok(commentRatings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentRatingResponseDto> getCommentRatingById(@PathVariable int id) {
        CommentRatingResponseDto commentRating = commentRatingService.getCommentRatingById(id);
        return ResponseEntity.ok(commentRating);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<CommentRatingResponseDto> createCommentRating(@RequestBody CommentRatingRequestDto commentRating) {
        CommentRatingResponseDto createdCommentRating = commentRatingService.createCommentRating(commentRating);
        return ResponseEntity.ok(createdCommentRating);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    public ResponseEntity<CommentRatingResponseDto> updateCommentRating(@PathVariable Integer id, @RequestBody CommentRatingRequestDto commentRating) {
        CommentRatingResponseDto updatedCommentRating = commentRatingService.updateCommentRating(id, commentRating);
        return ResponseEntity.ok(updatedCommentRating);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentRating(
            @PathVariable Integer id,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        // Extract the userId seamlessly from the authenticated principal
        Integer userId = currentUser.getUserId();

        commentRatingService.deleteCommentRating(id, userId);

        return ResponseEntity.ok("Comment rating deleted successfully");
    }
}
