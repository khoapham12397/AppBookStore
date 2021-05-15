package com.example.reviewreadurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle= getIntent().getBundleExtra("info");
        Student student= bundle.getParcelable("user");
        Toast.makeText(this,student.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        //thuc hien cai nay truoc :
        Log.d("FINSEC","finishing second activity");
        Intent intent =new Intent();
        intent.putExtra("name","khoa pham");
        setResult(RESULT_OK,intent);
        super.finish();
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }
}
