package com.example.reviewreadurl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentNews extends Fragment {
    NewsAdapter adapter;
    ListView listView;
    ArrayList<News> newsList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        newsList=new ArrayList<>();
        listView=view.findViewById(R.id.newsList);
        new ReadRSS().execute(MyUtils.pathNews);
        return  view;
    }
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

                    newsList.add(new News(title,link,pathImg));
                }
                adapter=new NewsAdapter(newsList);
                listView.setAdapter(adapter);
                MyUtils.setHeighListViewFitAll(listView);
            }
        }
    }

}
