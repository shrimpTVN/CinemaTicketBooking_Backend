package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.ProductDto;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(int id);

    public ProductDto createProduct(ProductDto productDto);
    public ProductDto updateProduct(Integer id, ProductDto productDto);
    public void updateProductStatus(Integer id, String status);
}
