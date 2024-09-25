package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Request.CreateProductRequest;
import com.myProject.ecommerce.Response.ApiResponse;
import com.myProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
        Product product = productService.createProduct(req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("product Deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts() throws ProductException {
        List<Product> products = (List<Product>) productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product reqProduct) throws ProductException{
        Product product = productService.updateProduct(productId, reqProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
        for(CreateProductRequest product : req){
            productService.createProduct(product);
        }
        ApiResponse res = new ApiResponse();
        res.setStatus(true);
        res.setMessage("Products created successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
