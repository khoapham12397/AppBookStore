package com.example.reviewreadurl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CartAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return Cart.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book= Cart.cartItems.get(position).getBook();
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.cart_item,parent,false);
        ImageView img=view.findViewById(R.id.imgCartItem);
        TextView txtName=view.findViewById(R.id.nameCartItem);
        TextView txtPrice=view.findViewById(R.id.priceCartItem);
        NumberPicker numberPicker=view.findViewById(R.id.numberCartItem);
        Picasso.with(parent.getContext()).load(book.getPathImg()).into(img);
        txtName.setText(book.getName());
        txtPrice.setText(MyUtils.formatMoney(String.valueOf(book.getPrice())));
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setValue(Cart.cartItems.get(position).getCount());
        return view;
    }

}
