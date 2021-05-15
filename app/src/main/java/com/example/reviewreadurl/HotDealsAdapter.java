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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotDealsAdapter extends RecyclerView.Adapter<HotDealsAdapter.HotDealsViewHolder> {
    Context context;
    ArrayList<Book> books;
    public HotDealsAdapter(Context context , ArrayList<Book> books){
        this.context=context;
        this.books=books;
    }
    @NonNull
    @Override
    public HotDealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.book_item_small_square,viewGroup,false);
        return new HotDealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotDealsViewHolder viewHolder, int i) {
        Picasso.with(context).load(books.get(i).getPathImg()).into(viewHolder.imgBook);
        viewHolder.txtName.setText(books.get(i).getName());
        viewHolder.txtPrice.setText(MyUtils.formatMoney(String.valueOf(books.get(i).getPrice())));
        final int pos=i;
        final Book book= books.get(i);
        //nhu vay thi co th//o
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,books.get(pos).getName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("bookInfo",book);
                intent.putExtra("info",bundle);
                context.startActivity(intent);
            }
        });
    }
    //ro rang la viec sinh ra 1 Thread moi van duoc dung va van truy xuat duoc cac resource

    @Override
    public int getItemCount() {
        return books.size();
    }

    class HotDealsViewHolder extends RecyclerView.ViewHolder{
        //sau do: thuc hien :
        ImageView imgBook;
        TextView txtName;
        TextView txtPrice;
        public HotDealsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook=itemView.findViewById(R.id.imgSmallSquare);
            txtName=itemView.findViewById(R.id.nameSmallSquare);
            txtPrice=itemView.findViewById(R.id.priceSmallSquare);
        }
    }
}
