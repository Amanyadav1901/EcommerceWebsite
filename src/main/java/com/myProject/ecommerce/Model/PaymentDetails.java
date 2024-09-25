package com.myProject.ecommerce.Model;

public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkRefernceId;
    private String razorpayPaymentId;
    private String getRazorpayPaymentLinkStatus;

    public PaymentDetails() {
    }

    public PaymentDetails(String status, String paymentMethod, String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkRefernceId, String razorpayPaymentId, String getRazorpayPaymentLinkStatus) {
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
        this.razorpayPaymentLinkRefernceId = razorpayPaymentLinkRefernceId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.getRazorpayPaymentLinkStatus = getRazorpayPaymentLinkStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentLinkRefernceId() {
        return razorpayPaymentLinkRefernceId;
    }

    public void setRazorpayPaymentLinkRefernceId(String razorpayPaymentLinkRefernceId) {
        this.razorpayPaymentLinkRefernceId = razorpayPaymentLinkRefernceId;
    }

    public String getGetRazorpayPaymentLinkStatus() {
        return getRazorpayPaymentLinkStatus;
    }

    public void setGetRazorpayPaymentLinkStatus(String getRazorpayPaymentLinkStatus) {
        this.getRazorpayPaymentLinkStatus = getRazorpayPaymentLinkStatus;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }
}
