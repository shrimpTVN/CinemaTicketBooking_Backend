package com.cinema.ticketbooking.media.service.impl;

import com.cinema.ticketbooking.media.service.ImageStorageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageStorageServiceImpl implements ImageStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadTempImage(MultipartFile file) throws IOException {
        // Upload to a specific 'temp-cinema-uploads' folder
        Map params = ObjectUtils.asMap(
                "folder", "temp-cinema-uploads",
                "resource_type", "image"
        );

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        return uploadResult.get("secure_url").toString();
    }

    @Override
    public String promoteToPermanent(String tempUrl) throws Exception {
        // In a real scenario, you extract the public_id from the URL
        // and use cloudinary.uploader().rename() to move it to "movie-posters"
        // For simplicity, we assume the URL is ready to be saved to MySQL.
        return tempUrl;
    }
}