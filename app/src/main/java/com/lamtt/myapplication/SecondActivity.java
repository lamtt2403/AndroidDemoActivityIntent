package com.lamtt.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvReceive;
    Button btTakePicture;
    ImageView ivResult;
    private final int CAMERA_REQUEST_CODE = 1997;
    private final int PERMISSTION_CAMERA_CODE = 2403;
    private final String TAG = "SecondActivity";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        String receive = getIntent().getStringExtra(FirstActivity.KEY_DATA);
        tvReceive.setText(receive);

        Student st = (Student) getIntent().getSerializableExtra(FirstActivity.KEY_OBJ);
        if (st != null) makeToastAndLog(st.toString());
    }


    @Override
    public void onClick(View v) {
            takePicture();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSTION_CAMERA_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeToastAndLog("permisson camera success!");
                takePicture();
                return;
            }
            permisstionCamera();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            ivResult.setImageBitmap(bm);
        }
    }

    private void init(){
        ivResult = findViewById(R.id.ivResult);
        tvReceive = findViewById(R.id.tvReceive);
        btTakePicture = findViewById(R.id.btTakePicture);
        btTakePicture.setOnClickListener(this);

        permisstionCamera();
    }

    private void takePicture(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private boolean permisstionCamera(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSTION_CAMERA_CODE);
                return true;
            }
            return false;
        }
        return true;
    }

    private void makeToastAndLog(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Log.d(TAG, str);
    }
}
