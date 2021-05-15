package com.example.reviewreadurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class ViewFull2ColsActivity extends AppCompatActivity {
   //co the nhu nay se duoc ok

    ArrayList<Book> books;
    GridView gridView;
    Grid2ColAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full2_cols);
        gridView=findViewById(R.id.gridView2Cols);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("info");
        books=bundle.getParcelableArrayList("books");
        //sau do co ngay lap tuc:
        adapter=new Grid2ColAdapter(books);
        gridView.setAdapter(adapter);
        MyUtils.setHeightGridViewFitAll(gridView);

    }
}
