package com.lamtt.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edData;
    Button btSecondActivity, btOpenUrl, btCallPhone, btSendObjto2Activity;
    public static final String KEY_DATA = "DATA";
    public static final String KEY_OBJ = "OBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }


    private void init(){
        edData = findViewById(R.id.edData);

        btSecondActivity = findViewById(R.id.btSecondActivity);
        btSecondActivity.setOnClickListener(this);
        btOpenUrl = findViewById(R.id.btOpenUrl);
        btOpenUrl.setOnClickListener(this);
        btCallPhone = findViewById(R.id.btCallPhone);
        btCallPhone.setOnClickListener(this);
        btSendObjto2Activity = findViewById(R.id.btSendObjTo2Activty);
        btSendObjto2Activity.setOnClickListener(this);
    }

    private void sendData(){
        String data = edData.getText().toString();
        Student st = new Student("B15DCCN021", "Nguyen Nam Anh");

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra(KEY_DATA, data);
//        i.putExtra(KEY_OBJ, st);
        startActivity(i);
    }

    private void sendObj(){
        Student st = new Student("B15DCCN021", "Le Tuan Hiep");
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra(KEY_OBJ, st);
        startActivity(i);
    }

    private void openUrl(){
       //"http://www.google.com";
        String url = edData.getText().toString();
        if (!url.startsWith("http://")){
            url = "http://" + url;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void openCallPhone(){
        String phone = edData.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},213);
        } else
            startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCallPhone:
                openCallPhone();
                break;

            case R.id.btSecondActivity:
                sendData();
                break;

            case R.id.btOpenUrl:
                openUrl();
                break;

            case R.id.btSendObjTo2Activty:
                sendObj();
                break;
        }
    }
}
