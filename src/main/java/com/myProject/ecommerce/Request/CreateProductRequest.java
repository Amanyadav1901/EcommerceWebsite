package com.myProject.ecommerce.Request;

import com.myProject.ecommerce.Model.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {

    private String title;
    private String description;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    private String brand;
    private double price;
    private double discountedPrice;
    private int discountPercent;
    private int quantity;
    private String color;
    private Set<Size> size = new HashSet<>();
    private String imageUrl;


    public CreateProductRequest() {
    }

    public CreateProductRequest(String title, String description, String topLevelCategory, String thirdLevelCategory, String secondLevelCategory, String brand, double price, double discountedPrice, int discountPercent, int quantity, String color, Set<Size> size, String imageUrl) {
        this.title = title;
        this.description = description;
        this.topLevelCategory = topLevelCategory;
        this.thirdLevelCategory = thirdLevelCategory;
        this.secondLevelCategory = secondLevelCategory;
        this.brand = brand;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopLevelCategory() {
        return topLevelCategory;
    }

    public void setTopLevelCategory(String topLevelCategory) {
        this.topLevelCategory = topLevelCategory;
    }

    public String getSecondLevelCategory() {
        return secondLevelCategory;
    }

    public void setSecondLevelCategory(String secondLevelCategory) {
        this.secondLevelCategory = secondLevelCategory;
    }

    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
