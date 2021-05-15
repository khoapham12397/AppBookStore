package com.example.reviewreadurl;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerPagerAdapter extends PagerAdapter {

    ArrayList<String> paths;
    public BannerPagerAdapter(ArrayList<String> paths){
        this.paths=paths;
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(container.getContext());
        View view= inflater.inflate(R.layout.banner_item,container,false);
        ImageView img=view.findViewById(R.id.imageBanner);
        if(paths.size()>0) {
            int t = -1;
            if (position < paths.size()) t = position;
            else t = paths.size() - 1;
            Picasso.with(container.getContext()).load(paths.get(t)).into(img);
        }else{
            int idRes=0;
            if(position==0) idRes=R.drawable.nhac_hoa;
            else if(position==1) idRes=R.drawable.nhac_han;
            else idRes=R.drawable.nhac_au_my;
            img.setImageResource(idRes);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
