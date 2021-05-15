package com.example.reviewreadurl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
     ArrayList<News> newsList;
     public  NewsAdapter(ArrayList<News> newsList){
         this.newsList=newsList;
     }
    @Override
    public int getCount() {
        return newsList.size();
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
         final ViewHolder viewHolder;
         if(view==null){
             LayoutInflater inflater= LayoutInflater.from(parent.getContext());
             view=inflater.inflate(R.layout.news_item,parent,false);
             viewHolder=new ViewHolder();
             viewHolder.imageView=view.findViewById(R.id.imgNews);
             viewHolder.title=view.findViewById(R.id.titleNews);
             viewHolder.intro=view.findViewById(R.id.introNews);
             view.setTag(viewHolder);
         }else {
             viewHolder= (ViewHolder) view.getTag();
         }
         viewHolder.title.setText(newsList.get(position).getTitle());
        Picasso.with(parent.getContext()).load(newsList.get(position).getPathImg()).into(viewHolder.imageView);
        return view;
    }
    class ViewHolder{
         ImageView imageView;
         TextView title;
         TextView intro;
    }
}
