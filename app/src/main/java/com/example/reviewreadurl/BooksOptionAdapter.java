package com.example.reviewreadurl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BooksOptionAdapter extends RecyclerView.Adapter<BooksOptionAdapter.GeneralViewHolder> {
    Context context;
    public ArrayList<Book> books;

    //sau do set up 1 cai list khac dung la nhu vay ???

    public BooksOptionAdapter(Context context,ArrayList<Book> books){
        this.context=context;
        this.books=books;

    }
    @NonNull
    @Override
    public BooksOptionAdapter.GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.pair_book_item,viewGroup,false);

        return new BooksOptionAdapter.GeneralViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull BooksOptionAdapter.GeneralViewHolder viewHolder, int i) {
        final Book book0=books.get(2*i),book1=books.get(2*i+1);
        Picasso.with(context).load(book0.getPathImg()).into(viewHolder.img0);
        Picasso.with(context).load(book1.getPathImg()).into(viewHolder.img1);
        viewHolder.name0.setText(book0.getName());
        viewHolder.name1.setText(book1.getName());
        viewHolder.price0.setText(MyUtils.formatMoney(String.valueOf(book0.getPrice())));
        viewHolder.price1.setText(MyUtils.formatMoney(String.valueOf(book1.getPrice())));
        final String name0 =book0.getName(),name1= book1.getName();

        viewHolder.img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("bookInfo",book0);
                intent.putExtra("info",bundle);


                context.startActivity(intent);
            }
        });
        viewHolder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("bookInfo",book1);
                intent.putExtra("info",bundle);


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size()/2;
    }

    class GeneralViewHolder extends RecyclerView.ViewHolder{
        ImageView img0,img1;
        TextView name0,name1,price0,price1;
        public GeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            img0=itemView.findViewById(R.id.imgSmallSquare0);
            img1=itemView.findViewById(R.id.imgSmallSquare1);
            name0=itemView.findViewById(R.id.nameSmallSquare0);
            name1=itemView.findViewById(R.id.nameSmallSquare1);
            price0=itemView.findViewById(R.id.priceSmallSquare0);
            price1=itemView.findViewById(R.id.priceSmallSquare1);

        }
    }
}
