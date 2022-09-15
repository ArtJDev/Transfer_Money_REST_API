package ru.netology.model;

import org.springframework.data.annotation.Id;

public class Card {
    @Id
    private long number;
    private String validtill;
    private String cvv;
    private int amount;
    private String currency;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getValidtill() {
        return validtill;
    }

    public void setValidtill(String validtill) {
        this.validtill = validtill;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

//    @Override
//    public String toString() {
//        return "Card{" +
//                "number=" + number +
//                ", validtill='" + validtill + '\'' +
//                ", cvv='" + cvv + '\'' +
//                ", amount=" + amount +
//                ", currency='" + currency + '\'' +
//                '}';
//    }
}