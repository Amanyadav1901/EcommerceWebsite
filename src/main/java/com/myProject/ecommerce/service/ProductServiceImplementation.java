package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Category;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Repository.CategoryRepository;
import com.myProject.ecommerce.Repository.ProductRepository;
import com.myProject.ecommerce.Request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImplementation implements ProductService{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;



    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {

        Category topLevel = categoryRepository.findByName(createProductRequest.getTopLevelCategory());

        if(topLevel == null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(createProductRequest.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(createProductRequest.getTopLevelCategory(), topLevel.getName());

        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(createProductRequest.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category ThirdLevel = categoryRepository.findByNameAndParent(createProductRequest.getTopLevelCategory(), secondLevel.getName());

        if(ThirdLevel == null){
            Category ThirdLevelCategory = new Category();
            ThirdLevelCategory.setName(createProductRequest.getThirdLevelCategory());
            ThirdLevelCategory.setParentCategory(secondLevel);
            ThirdLevelCategory.setLevel(3);

            ThirdLevel = categoryRepository.save(ThirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setColor(createProductRequest.getColor());
        product.setDescription(createProductRequest.getDescription());
        product.setSizes(createProductRequest.getSize());
        product.setPrice(createProductRequest.getPrice());
        product.setDiscountPercent(createProductRequest.getDiscountPercent());
        product.setDiscountedPrice(createProductRequest.getDiscountedPrice());
        product.setBrand(createProductRequest.getBrand());
        product.setImageUrl(createProductRequest.getImageUrl());
        product.setCategory(ThirdLevel);
        product.setCreatedAt(LocalDateTime.now());
        product.setBrand(createProductRequest.getBrand());
        product.setQuantity(createProductRequest.getQuantity());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }


    @Override
    public void deleteProduct(Long ProductId) throws ProductException {
        Product product = findProductById(ProductId);
        product.getSizes().clear();
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Long ProductId, Product reqProduct) throws ProductException {

        Product product = findProductById(ProductId);
        if(reqProduct.getQuantity() != 0 && reqProduct.getQuantity() != product.getQuantity()){
            product.setQuantity(reqProduct.getQuantity());
        }
        if(reqProduct.getPrice() != 0 && reqProduct.getPrice() != product.getPrice()){
            product.setPrice(reqProduct.getPrice());
        }
        if(reqProduct.getDiscountPercent() != 0 && reqProduct.getDiscountPercent() != product.getDiscountPercent()){
            product.setDiscountPercent(reqProduct.getDiscountPercent());
        }
        if(reqProduct.getDiscountedPrice() != 0 && reqProduct.getDiscountedPrice() != product.getDiscountedPrice()){
            product.setDiscountedPrice(reqProduct.getDiscountedPrice());
        }
        if(reqProduct.getColor() != null && !reqProduct.getColor().equals(product.getColor())){
            product.setColor(reqProduct.getColor());
        }
        if(reqProduct.getBrand() != null && !reqProduct.getBrand().equals(product.getBrand())){
            product.setBrand(reqProduct.getBrand());
        }
        if(reqProduct.getImageUrl() != null && !reqProduct.getImageUrl().equals(product.getImageUrl())){
            product.setImageUrl(reqProduct.getImageUrl());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long ProductId) throws ProductException {
        Optional<Product> opt = productRepository.findById(ProductId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new ProductException("Product Not Found with id"+ProductId);
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return List.of();
    }

    @Override
    public Page<Product> findAllProducts(String Category, List<String> colors, List<String> sizes, int minPrice, int maxPrice, int minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(Category, minPrice, maxPrice, minDiscount, sort);

        if(!colors.isEmpty()){
            products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).toList();

        }
        if(stock != null){
            if(stock.equals("in_stock")){
                products = products.stream().filter(p -> p.getQuantity() > 0).toList();
            }else if (stock.equalsIgnoreCase("out_of_stock")){
                products = products.stream().filter(p->p.getQuantity()<1).toList();
            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min((startIndex + pageable.getPageSize()), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }

    @Override
    public List<Product> findAllProducts() throws ProductException {
        List<Product> products = productRepository.filterProducts(null, 0, 0, 0, null);
        return products;
    }
}
