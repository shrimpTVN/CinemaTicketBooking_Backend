package com.cinema.ticketbooking.media.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageStorageService {
    /**
     * Uploads a file to a temporary holding area and returns the URL.
     */
    String uploadTempImage(MultipartFile file) throws IOException;

    /**
     * Moves an image from the temporary area to the permanent movie-posters folder.
     */
    String promoteToPermanent(String tempUrl) throws Exception;
}