package com.example.reviewreadurl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

public class FragmentOptions extends Fragment {
    CategoriesListAdapter categoriesAdapter;
    RecyclerView categoriesRecyclerView;
    RecyclerView booksRecyclerView;
    BooksOptionAdapter booksAdapter;
    ArrayList<Book> books;
    TextView viewMoreOptions;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_options,container,false);
        categoriesRecyclerView= view.findViewById(R.id.categoriesRecyclerView);
        booksRecyclerView=view.findViewById(R.id.booksOptionRecyclerView);
        viewMoreOptions=view.findViewById(R.id.viewMoreOptions);
        categoriesAdapter=new CategoriesListAdapter(getContext());
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        books=new ArrayList<>();
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, MyUtils.getProductsAddress(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                MyUtils.getBooks(response,books);
                booksAdapter=new BooksOptionAdapter(getContext(),books);
                booksRecyclerView.setAdapter(booksAdapter);
                LinearLayoutManager l0=new LinearLayoutManager(FragmentOptions.this.getContext());
                LinearLayoutManager l1=new LinearLayoutManager(FragmentOptions.this.getContext());
                l0.setOrientation(LinearLayoutManager.HORIZONTAL);
                l1.setOrientation(LinearLayoutManager.HORIZONTAL);

                categoriesRecyclerView.setLayoutManager(l0);
                booksRecyclerView.setLayoutManager(l1);

                categoriesAdapter.setBooksAdapter(booksAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyUtils.requestQueue.add(request);
        viewMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=FragmentOptions.this.getContext();
                Intent intent=new Intent(context,ViewFull2ColsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("books",books);
                intent.putExtra("info",bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
