package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest createProductRequest);
    public void deleteProduct(Long ProductId) throws ProductException;
    public Product updateProduct(Long ProductId, Product reqProduct) throws ProductException;
    public Product findProductById(Long ProductId) throws ProductException;
    public List<Product> findProductByCategory(String category) throws ProductException;
    public Page<Product> findAllProducts(String Category, List<String> colors, List<String> sizes, int minPrice, int maxPrice,
                                         int minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException;
    public List<Product> findAllProducts() throws ProductException;
}
