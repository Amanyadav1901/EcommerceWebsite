package com.myProject.ecommerce.Repository;

import com.myProject.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p "
            + "WHERE (p.category.name =:category OR :category = '') "
            + "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) "
            + "AND ((:minDiscount IS NULL) OR (p.discountPercent >= :minDiscount)) "
            + "ORDER BY "
            + "CASE WHEN :sort = 'priceLowToHigh' THEN p.discountedPrice END ASC, "
            + "CASE WHEN :sort = 'priceHighToLow' THEN p.discountedPrice END DESC")
    List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);
}