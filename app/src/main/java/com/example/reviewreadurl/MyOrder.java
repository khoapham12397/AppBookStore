package com.example.reviewreadurl;

import java.util.ArrayList;

public class MyOrder {
    //dau tien can co :
    String email=null;
    String password=null;
    int total_payment=0;
    ArrayList<OrderItem> order_items=null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(int total_payment) {
        this.total_payment = total_payment;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return order_items;
    }

    public void setCart_items(ArrayList<OrderItem> order_items) {
        this.order_items = order_items;
    }

    public MyOrder(String email, String password, int total_payment, ArrayList<OrderItem> order_items) {
        this.email = email;
        this.password = password;
        this.total_payment = total_payment;
        this.order_items = order_items;
    }
    public MyOrder(){

    }

}
