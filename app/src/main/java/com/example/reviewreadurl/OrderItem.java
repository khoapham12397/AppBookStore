package com.example.reviewreadurl;

public class OrderItem {
    int id_book;
    int count;
    int price;

    public int getId_book() {
        return id_book;
    }

    public OrderItem(int id_book, int count, int price) {
        this.id_book = id_book;
        this.count = count;
        this.price = price;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
