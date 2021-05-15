package com.example.reviewreadurl;

import java.util.ArrayList;

public class Cart {
    static ArrayList<CartItem> cartItems;
    //ta nen dau thong tin nao vao ???
    //chi can 1 cai thoi dung la nhu vay :
    //dau tien no la 1 list all :
    //vi du :


    public static void init(){
        cartItems=new ArrayList<>();
    }
    public static int getCount(){
        return cartItems.size();
    }
    //vi du neu ma da ton tai roi thi khong duoc dung la nhu va y:

    public static void addItem(Book book,int count){
        for(int i=0;i< cartItems.size();i++){
            if(cartItems.get(i).getBook().getId()==book.getId()){
                cartItems.get(i).count++;
                return;
            }
        }
        CartItem item=new CartItem(book,count,book.getPrice());

        cartItems.add(item);
    }
    public static void deleteItem(int id){
        for(int i=0;i<cartItems.size();i++) {
            if (cartItems.get(i).getBook().getId() == id) {
                cartItems.remove(i);
                break;
            }
        }
    }
    public static void updateItem(int id,int count){
        for(int i=0;i<cartItems.size();i++){
            CartItem item=cartItems.get(i);
            if(item.getBook().getId()==id){
                item.setCount(count);
            }
        }
    }
    public static boolean hasProductId(int id){
        for(int i=0;i<cartItems.size();i++){
            CartItem item=cartItems.get(i);
            if(item.getBook().getId()==id)
                return true;
        }
        return false;
    }
}
