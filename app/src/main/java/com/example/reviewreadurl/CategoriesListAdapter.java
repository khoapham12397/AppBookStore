package com.example.reviewreadurl;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CateViewHolder> {

    BooksOptionAdapter booksAdapter;
    ArrayList<String> categories;
    Context context;
    int cur=0;
    ArrayList<CateViewHolder> viewHolders;
    public void setBooksAdapter(BooksOptionAdapter adapter){
        this.booksAdapter=adapter;

    }
    public  CategoriesListAdapter(Context context){
        //sau do no chi co the truyen duy nhat so 0????

        categories=new ArrayList<>();
        categories.add("Tổng hợp");
        categories.add("Toán Olympic");
        categories.add("Toán THPT Quốc gia");
        categories.add("Toán THPT");
        categories.add("Toán THCS");
        categories.add("Toán Cao cấp");
        categories.add("Toán ứng dụng");
        categories.add("Thuật toán");
        categories.add("Lập trình thi đấu");
        this.context=context;
        viewHolders=new ArrayList<>();
    }
    //no goi dung 1 thang nen bi nhu vay :
    //ok nen lam the nao ???

    @NonNull
    @Override
    public CateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.category_item,viewGroup,false);
        CateViewHolder viewHolder=new CateViewHolder(view);
        viewHolders.add(viewHolder);
        Log.d("AAA","Created ViewHolder at "+String.valueOf(i));
        return viewHolder;
    }

    //no phai tao ra viewHolder truoc???
    //dung la nhu vay :
    //do
    //ban than cua no chi co so 0 ????
    //why???
    //ro rang vao bindthi khac dung do do ,phai lam sao ???


    @Override
    public void onBindViewHolder(@NonNull final CateViewHolder viewHolder, int i) {
        viewHolder.txtName.setText(categories.get(i));
        final int pos=i;
        if(i==cur){
            viewHolder.txtName.setTextColor(Color.WHITE);
            viewHolder.txtName.setBackgroundColor(Color.BLACK);
        }else{
            viewHolder.txtName.setTextColor(Color.BLACK);
            viewHolder.txtName.setBackgroundColor(Color.LTGRAY);
        }

        viewHolder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cho nay se co chut kho chiu

                for(int i=0;i<viewHolders.size();i++){
                    viewHolders.get(i).txtName.setTextColor(Color.BLACK);
                    viewHolders.get(i).txtName.setBackgroundColor(Color.LTGRAY);
                }

                viewHolder.txtName.setTextColor(Color.WHITE);
                viewHolder.txtName.setBackgroundColor(Color.BLACK);

                cur=pos;
                String path=MyUtils.pathServer+"getProductsCate.php?cate_id="+String.valueOf(pos);
                if(pos==0) path=MyUtils.getProductsAddress();
                JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, path, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        booksAdapter.books=new ArrayList<>();
                        ArrayList<Book> books=booksAdapter.books;
                        MyUtils.getBooks(response,books);
                        if(books.size()>0){
                            booksAdapter.notifyDataSetChanged();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MyUtils.requestQueue.add(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CateViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;

        public CateViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.categoryName);
        }

    }
}
