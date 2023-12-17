package com.example.ngabur;

public class Transaction {
    private String id;
    private String transactionDate;
    private String checkInDate;
    private String checkOutDate;
    private String room;
    private DataClass hotel;
    private String paymentType;
    private int totalharga;
    private String Status;

    public Transaction() {

    }

    public Transaction(String id, String transactionDate, String checkInDate, String checkOutDate, String room, DataClass hotel, String paymentType, String Status, int totalharga) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.hotel = hotel;
        this.paymentType = paymentType;
        this.Status = Status;
        this.totalharga = totalharga;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataClass getHotel() {
        return hotel;
    }

    public void setHotel(DataClass hotel) {
        this.hotel = hotel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(int totalharga) {
        this.totalharga = totalharga;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
