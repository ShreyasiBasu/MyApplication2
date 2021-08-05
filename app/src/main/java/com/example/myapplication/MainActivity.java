 package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity implements MainActivity1 {

    Button btn1;
    ImageView imageView;
    //int SELECT_IMAGE_CODE=1;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIEWS
        btn1=findViewById(R.id.btn1);
        imageView=findViewById(R.id.pickedimage);

        //HANDLE BUTTON CLICK
        btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        //check runtime permission
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                //permission not granted, request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                //SHOW POP UP FOR RUNTIME PERMISSION
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else {
                //permission already granted
                pickImageFromGallery();
            }

        }

        else {
            //system os is less than marshmallow
            pickImageFromGallery();
        }
    }
    });
}

     private void pickImageFromGallery() {
         //intent to pick image
         Intent intent = new Intent(Intent.ACTION_PICK);
         intent.setType("image/*");
         startActivityForResult(intent, IMAGE_PICK_CODE);
     }

    //handle result of runtime permission

    @Override
    public void onRequestPermissionResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
        case PERMISSION_CODE:{
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //PERMISSION WAS GRANTED
                pickImageFromGallery();
            }
            else{
                //permission was denied
                Toast.makeText(this, "Permission denied.....!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }
    //handle result of picked image
@Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
         //set image to image view
         imageView.setImageURI(data.getData());
     }
}}





















