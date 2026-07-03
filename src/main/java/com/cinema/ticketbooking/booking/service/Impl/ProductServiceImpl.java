package com.cinema.ticketbooking.booking.service.Impl;

import com.cinema.ticketbooking.booking.service.IProductService;
import com.cinema.ticketbooking.core.exception.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.ProductDto;
import com.cinema.ticketbooking.entity.Product;
import com.cinema.ticketbooking.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id is not exists: " + id));
        return transformToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setImage(productDto.image());
        product.setStatus( "ON");

        Product savedProduct = productRepository.save(product);
        return transformToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id is not exists: " + id));

        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setImage(productDto.image());

        Product updatedProduct = productRepository.save(product);
        return transformToDto(updatedProduct);
    }

    @Override
    public void updateProductStatus(Integer id, String status) {
        productRepository.findById(id)
                .ifPresent(product -> {
                    product.setStatus(status);
                    productRepository.save(product);
                });
    }

    private ProductDto transformToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                product.getStatus()
        );
    }
}
