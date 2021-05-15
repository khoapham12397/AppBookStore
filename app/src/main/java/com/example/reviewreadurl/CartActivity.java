package com.example.reviewreadurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CartActivity extends AppCompatActivity {
    ListView listCartItemView;
    CartAdapter cartAdapter;
    TextView txtOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        txtOrder=findViewById(R.id.order);
        listCartItemView=findViewById(R.id.listCartItems);
        cartAdapter=new CartAdapter();
        listCartItemView.setAdapter(cartAdapter);
        MyUtils.setHeighListViewFitAll(listCartItemView);
        //ngay tu luc bien dich la da xac dinh duoc roi dung la nhu vay dieu nay kha la tot ok
        //just do it ????
        //sau do=> tien hanh post len va get ra la duoc ???

        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<CartItem> cartItems=Cart.cartItems;
                if(cartItems==null || cartItems.size()==0) return;
                int total=0;
                ArrayList<OrderItem> items=new ArrayList<>();
                for(CartItem line : cartItems){
                    total*=line.getCount()*line.getPrice();
                    items.add(new OrderItem(line.getBook().getId(),line.getCount(),line.getPrice()));
                }
                MyOrder order=new MyOrder(MyUtils.email,MyUtils.pass,total,items);

                Gson gson =new Gson();
                final String strJson= gson.toJson(order);
                //da tien hanh check xong bay gio ta tien hanh thuc hien ben trong ??
                String pathURl=MyUtils.pathServer+"orderApp.php";
                StringRequest request=new StringRequest(Request.Method.POST, pathURl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DDD",response);
                        //du no la json cung ke di :

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("EEE",error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("my_order",strJson);
                        return params;
                    }
                };
                MyUtils.requestQueue.add(request);
            }
        });
    }
}
