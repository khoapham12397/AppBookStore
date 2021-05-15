package com.example.reviewreadurl;
//gui 1 loat cac van de ve cart len tren do la duoc :
//bao gom co cai gi do :
//sau do no get cai info ve
//trong nay ko co session -> ma no send thang cai account info to server -> xac dinh duoc
//thang nao de cho xu ly phu hop :
//sau do neu ta thiet lap 1 cai
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyUtils {
    public static String email="phamminhsontat@gmail.com";
    public static String pass="khoa123";


    public static String localIP="192.168.10.10";
    public static String pathNews="https://vnexpress.net/rss/so-hoa.rss";
    public static String pathServer="http://kmp97.000webhostapp.com/Server/book_store_project/";

    public static RequestQueue requestQueue;
    public static ExecutorService executorService=null;

    public static void initRequestQueue(Context context){
        //executorService= Executors.newFixedThreadPool(4);
        requestQueue= Volley.newRequestQueue(context);
    }
    public static String getContentURL(String pathURL){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            URL url=new URL(pathURL);
            InputStream is=url.openConnection().getInputStream();

            BufferedReader br= new BufferedReader(new InputStreamReader(is));
            String line;
            while((line=br.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getProductsAddress(){
        return "http://kmp97.000webhostapp.com/Server/book_store_project/getProducts.php";
    }
    public static Book convertJsonToBook(JSONObject o){
        Book book=null;
        try {
            book=new Book(o.getInt("id"),o.getString("name"),o.getString("author"),o.getInt("price"),o.getString("path_img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return book;
    }
    public static void setHeightGridViewFitAll(GridView gridView){
        ListAdapter adapter=gridView.getAdapter();

        if(adapter==null ) return;
        int l=adapter.getCount(),total=0;
        for(int i=0;i<l;i++){
            View view= adapter.getView(i,null,gridView);
            view.measure(0,0);
            total+= view.getMeasuredHeight();
        }
        total/=2;
        total+= 30*l;
        ViewGroup.LayoutParams params= gridView.getLayoutParams();
        params.height=total;
        gridView.setLayoutParams(params);
    }
    public static void setHeighListViewFitAll(ListView listView){
        ListAdapter adapter=listView.getAdapter();
        Log.d("AAA",String.valueOf(adapter.getCount()));
        if(adapter==null) return;
        int total=0;

        for(int i=0;i<adapter.getCount();i++){
            View view= adapter.getView(i,null,listView);
            view.measure(0,0);
            total+=view.getMeasuredHeight();
        }

        //dau tien can set up them cai gi do ???

        total+=(adapter.getCount())*(listView.getDividerHeight()+40);
        ViewGroup.LayoutParams layoutParams=listView.getLayoutParams();
        layoutParams.height=total;
        listView.setLayoutParams(layoutParams);

    }
    public static void getBooks(JSONArray jsonArray, ArrayList<Book> books){
        for(int i=0;i<jsonArray.length();i++){
            try {
                Book book=convertJsonToBook(jsonArray.getJSONObject(i));
                if(book!=null) books.add(book);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public static String formatMoney(String x){
        String s="";
        int t=0;
        for(int i=x.length()-1;i>=0;i--){
            t++;
            s+=x.charAt(i);
            if(t%3==0 && t!=x.length()) s+='.';
        }
        String res="";
        for(int i=s.length()-1;i>=0;i--) res+=s.charAt(i);
        return res + " đ";
    }
}
