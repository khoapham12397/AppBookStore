package com.example.reviewreadurl;

public class CartItem {
    Book book;
    public int count;
    int price;
    //ta biet la dieu nay khong phu hop ok just do it ???
    //dau tien ta can xay dung lau theo orderItem:

    //dung la nhu vay
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public CartItem(Book book, int count, int price) {
        this.book = book;
        this.count = count;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public CartItem(){

    }

}

