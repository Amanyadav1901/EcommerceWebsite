package com.myProject.ecommerce.Model;


public class Size {
    private String name;
    private int quantity;

    public Size() {
    }

    public Size(String size, int quantity) {
        this.name = size;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}
