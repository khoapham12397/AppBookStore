package com.example.reviewreadurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFullActivity extends AppCompatActivity {
    ListView listView;
    ListLineItemAdapter adapter;
    ArrayList<Book> books=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfull);
        listView=findViewById(R.id.listLineItem);
        Intent intent =getIntent();
        Bundle bundle= intent.getBundleExtra("info");
        books= bundle.getParcelableArrayList("books");

        adapter=new ListLineItemAdapter(books);
        listView.setAdapter(adapter);
        MyUtils.setHeighListViewFitAll(listView);
    }
}
