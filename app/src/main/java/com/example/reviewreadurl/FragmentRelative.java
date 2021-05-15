package com.example.reviewreadurl;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentRelative extends Fragment {
    HotDealsAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Book> books;
    TextView title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hot_deal,container,false);
        recyclerView=view.findViewById(R.id.hotDealsRecyclerView);
        title=view.findViewById(R.id.titleHotDeal);
        title.setTextColor(Color.BLACK);

        title.setText("Relative products");
        books=new ArrayList<>();
        //dau tien ta
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, MyUtils.getProductsAddress(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++){
                            Book book=null;
                            try {
                                book=MyUtils.convertJsonToBook(response.getJSONObject(i));
                                if(book!=null) books.add(book);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if(books.size()>0){
                            Context context =FragmentRelative.this.getContext();
                            adapter=new HotDealsAdapter(context,books);
                            recyclerView.setAdapter(adapter);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
                            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(layoutManager);
                        }
                        //tu day co duoc danh sach:

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyUtils.requestQueue.add(request);
        return view;
    }
}
