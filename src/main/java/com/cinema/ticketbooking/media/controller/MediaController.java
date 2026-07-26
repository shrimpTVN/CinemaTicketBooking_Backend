package com.cinema.ticketbooking.media.controller;

import com.cinema.ticketbooking.media.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final ImageStorageService imageStorageService;


    @PostMapping("/upload/movie-poster")
    public ResponseEntity<Map<String, String>> uploadMoviePoster(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.uploadImage(file, "movies");
            // Return JSON containing the URL
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/upload/seat-type-image")
    public ResponseEntity<Map<String, String>> uploadSeatTypeImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.uploadImage(file, "seat-types");
            // Return JSON containing the URL
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/upload/combo-image")
    public ResponseEntity<Map<String, String>> uploadComboImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.uploadImage(file, "combos");
            // Return JSON containing the URL
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path={"/upload/event-poster", "/upload/event-banner"})
    public ResponseEntity<Map<String, String>> uploadEventImages(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.uploadImage(file, "events");
            // Return JSON containing the URL
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/upload/hall-images")
    public ResponseEntity<List<String>> uploadMultipleImages(@RequestParam("files") List<MultipartFile> files) {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                // Utilizing the temp bin pattern from our previous session
                String url = imageStorageService.uploadImage(file, "halls");
                uploadedUrls.add(url);
            } catch (Exception e) {
                // ARCHITECT'S NOTE: In a production system, you might want to
                // return a detailed object showing which files succeeded and which failed,
                // but for now, we will skip failed files and return successful ones.
                System.err.println("Failed to upload a file: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(uploadedUrls);
    }
}