package com.example.reviewreadurl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListLineItemAdapter extends BaseAdapter {
    //pass 1 list:
    ArrayList<Book> books;
    public ListLineItemAdapter(ArrayList<Book> books){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            view=inflater.inflate(R.layout.book_line_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imgBook=view.findViewById(R.id.imgBookLineItem);
            viewHolder.txtName=view.findViewById(R.id.nameBookLineItem);
            viewHolder.txtPrice=view.findViewById(R.id.priceBookLineItem);
            viewHolder.txtAuthor=view.findViewById(R.id.authorBookLineItem);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        Picasso.with(parent.getContext()).load(books.get(position).getPathImg()).into(viewHolder.imgBook);
        viewHolder.txtName.setText(books.get(position).getName());
        viewHolder.txtAuthor.setText(books.get(position).getAuthor());
        viewHolder.txtPrice.setText(String.valueOf(books.get(position).getPrice())+" đ");

        return view;
    }
    class ViewHolder{
        ImageView imgBook;
        TextView txtName;
        TextView txtAuthor;
        TextView txtPrice;
    }
}
