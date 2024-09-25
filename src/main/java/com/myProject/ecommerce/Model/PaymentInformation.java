package com.myProject.ecommerce.Model;

import jakarta.persistence.Column;

public class PaymentInformation {

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cardholder_name")
    private String cardHolderName;
    @Column(name = "expiry_date")
    private String expiryDate;
    @Column(name = "cvv")
    private String cvv;

    public PaymentInformation() {
    }

    public PaymentInformation(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
}
