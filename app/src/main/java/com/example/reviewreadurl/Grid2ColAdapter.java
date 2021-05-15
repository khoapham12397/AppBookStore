package com.example.reviewreadurl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Grid2ColAdapter extends BaseAdapter {
    ArrayList<Book> books;
    public Grid2ColAdapter(ArrayList<Book> books){
        this.books=books;
    }
    @Override
    public int getCount() {
        return books.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            view=inflater.inflate(R.layout.book_item_big_square,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.txtName=view.findViewById(R.id.nameBigSquareItem);
            viewHolder.txtPrice=view.findViewById(R.id.priceBigSquareItem);
            viewHolder.img=view.findViewById(R.id.imgBigSquareItem);
            view.setTag(viewHolder);
        }else viewHolder= (ViewHolder) view.getTag();
        final Context context=parent.getContext();
        viewHolder.txtName.setText(books.get(position).getName());
        viewHolder.txtPrice.setText(MyUtils.formatMoney(String.valueOf(books.get(position).getPrice())));
        Picasso.with(context).load(books.get(position).getPathImg()).into(viewHolder.img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("bookInfo",books.get(position));
                intent.putExtra("info",bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        TextView txtName;
        TextView txtPrice;
        ImageView img;
    }
}
