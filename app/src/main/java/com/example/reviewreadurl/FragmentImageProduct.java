package com.example.reviewreadurl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentImageProduct extends Fragment {
    ContainBook containBook;
    Book book;
    ViewPager viewPager;
    BannerPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_product_image,container,false);
        book=containBook.getBook();
        viewPager=view.findViewById(R.id.imagePager);
        ArrayList<String> paths=new ArrayList<>();
        paths.add(book.getPathImg());
        adapter=new BannerPagerAdapter(paths);
        viewPager.setAdapter(adapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        containBook= (ContainBook) context;
    }

}
