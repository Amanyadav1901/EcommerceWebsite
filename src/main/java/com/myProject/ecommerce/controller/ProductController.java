package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam String category,
            @RequestParam List<String> color,
            @RequestParam List<String> size,
            @RequestParam int minPrice,
            @RequestParam int maxPrice,
            @RequestParam int minDiscount,
            @RequestParam String sort,
            @RequestParam String stock,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) throws ProductException {
        Page<Product> res = productService.findAllProducts(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product res = productService.findProductById(productId);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }


}
