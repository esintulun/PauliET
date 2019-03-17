package com.example.esintulun.pauli;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class Cameratest2Activity extends AppCompatActivity {

    ImageView ivCamera;
    static final int REQUEST_TAKE_PHOTO = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameratest2);
        ivCamera =findViewById(R.id.ivCameraTest2);
        dispatchTakePictureIntent();
    }
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        Log.i("onActivityResult", " dispatchTakePictureIntent" );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("onActivityResult", "Intent data; " + data + "resultCode:" + resultCode + "requestCode: " + requestCode);
        Log.i("onActivityResult", "Intent getdata: ; " + data.getData());


        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Log.i("onActivityResult", "okey; " + data);
            ivCamera.setImageBitmap((Bitmap) data.getExtras().get("data"));


        }
    }
}
