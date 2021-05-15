package com.example.reviewreadurl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.gesture.GestureOverlayView;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilder;

public class MainActivity extends AppCompatActivity {

    int xc=0;
    boolean mBound=false;
    final int REQUEST_TO_SECOND=1;
    ExampleService exampleService;
    BottomNavigationView searchNavigation;

    ViewPager viewPager;
    TabLayout tabLayout;
    MainPagerAdapter mainPagerAdapter;
    ImageView cartView;


    private ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ExampleService.LocalBinder binder= (ExampleService.LocalBinder) service;
            exampleService=binder.getService();
            mBound=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        searchNavigation=findViewById(R.id.topNavigation);
        cartView=findViewById(R.id.btnCart);
        //vai thu can xac dinh truoc :
        //vi du nhu la:

        MyUtils.initRequestQueue(this);
        Cart.init();
        viewPager=findViewById(R.id.mainPager);
        tabLayout=findViewById(R.id.mainTab);
        mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("News");
        cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CartActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        //sendData();
        //getAccount();
    }
    void sendData(){
        String urlPath="http://192.168.10.10:8080/Review2/vidu1.php";
        StringRequest request =new StringRequest(Request.Method.POST, urlPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Log.d("DDD","Count: "+String.valueOf(jsonArray.length()));
                    Log.d("DDD",jsonArray.getJSONObject(0).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("EEE",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("name","khoa pham");
                params.put("library",createJson());

                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String str= new String(response.data,HttpHeaderParser.parseCharset(response.headers));
                    Log.d("DDD",str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);

            }
        };
        MyUtils.requestQueue.add(request);
    }
    void getAccount()  {
        String urlPath="http://192.168.10.10:8080/Review2/getAcc.php";
        //dau tien no di duoc de day ??

        StringRequest request=new StringRequest(Request.Method.GET, urlPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DDD", "In onResponse method and response : "+response);
                try {

                    JSONObject jsonObject=new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("EEE",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.d("DDD","In parse method");
                try {
                    String str=new String(response.data,HttpHeaderParser.parseCharset(response.headers));
                    Log.d("DDD",str+" here");
                    //dau tien set up: no la cai i ???

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        MyUtils.requestQueue.add(request);
    }
    String createJson(){

        Gson gson=new Gson();
        ArrayList<Book> books=new ArrayList<>();
        books.add(new Book(1,"Chuyen khao day so","nguyen tai chung",150000,"ckds.jpg"));
        books.add(new Book(1,"to hop va quy nap","nguyen van nho",150000,"ckds.jpg"));
        MyLibrary library=new MyLibrary("khoa pham",books);
        String str=gson.toJson(library);
        /*
        try {
            JSONObject jsonObject=new JSONObject(str);
            JSONArray array=jsonObject.getJSONArray("books");
            //Toast.makeText(this,array.getJSONObject(0).getString("name"),Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return str;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,ExampleService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DDD","Hello");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("STOP","Activity 1 is stopping");
        unbindService(connection);
        mBound=false;
    }



    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


    /*
    private class ReadRSS extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String url0= strings[0];
            StringBuilder stringBuilder =new StringBuilder();
            try {
                URL url= new URL(url0);

                InputStream is=url.openConnection().getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                while((line=br.readLine())!=null) {
                    stringBuilder.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //can xay dung cay Dom:
            XMLDomParser parser=new XMLDomParser();
            Document document=parser.getDocument(s);
            if(document!=null){

                NodeList nodes=document.getElementsByTagName("item");
                for(int i=0;i<nodes.getLength();i++){
                    Element element= (Element) nodes.item(i);
                    String title=parser.getValue(element,"title");
                    String link=parser.getValue(element,"link" );
                    String pathImg=parser.getPathImg(element);
                    parser.getPathImg(element);

                }

            }
        }
    }
    */

}
