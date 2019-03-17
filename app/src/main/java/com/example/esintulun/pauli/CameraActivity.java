package com.example.esintulun.pauli;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    EditText etName, etPassword;
    ImageView mImageView;

    String realPath;

    String mCurrentPhotoPath;
    public static final String EXTRA_MESSAGE_NAME = "name";
    public static final String EXTRA_MESSAGE_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

      /*  final TextView textView = (TextView) findViewById(R.id.bntLogin);

        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);*/

        //Bitmap myAngryImage = BitmapFactory.decodeResource(getResources(), R.drawable.et); !!

        mImageView = findViewById(R.id.ivCamera);
        dispatchTakePictureIntent();

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.i("IP", "ip: " + ip);
    }

    public void bntLogin(View v) {
        Intent intent = new Intent(this, DatenbankActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String name = etName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE_NAME, name);
        String password = etPassword.getText().toString();
        intent.putExtra(EXTRA_MESSAGE_PASSWORD, password);
        startActivity(intent);
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);

                List<ResolveInfo> resolvedIntentActivities = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                    String packageName = resolvedIntentInfo.activityInfo.packageName;

                    grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                //However you must not put the extra output into the intent,
                // because then the data goes into the URI instead of the data variable.
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.i("photo... " + "photoFile: ", photoFile.toString());
                Log.i("photo... " + "photoURI: ", photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);

        Log.i("onActivityResult", "Intent data; " + data + "resultCode:" + resultCode + "requestCode: " + requestCode);
        Log.i("onActivityResult", "Intent getdata: ; " + data.getData()); content://media/external/images/media/6229

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Log.i("onActivityResult", "okey; " + data);


          /* // FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }*/
            Bundle extras = data.getExtras();
            Log.i("onactivity", "ja.. " + extras.toString());

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // Show Uri path based on Image
            Toast.makeText(this, "Here " + tempUri, Toast.LENGTH_LONG).show();

            // Show Uri path based on Cursor Content Resolver
            Toast.makeText(this, "Real path for URI : " + getRealPathFromURI(tempUri), Toast.LENGTH_LONG).show();
            Log.i("onActivityResult", "uri.. " + tempUri);// content://media/external/images/media/6230
            Log.i("onActivityResult", "RealPath:-) " + getRealPathFromURI(tempUri)); ///storage/emulated/0/DCIM/Camera/1548231358482.jpg

           // new DatenbankActivity(mCurrentPhotoPath).execute("someParams");
        }

    }


    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath(); ///storage/emulated/0/Android/data/com.example.student.schulaufgabezettel/files/Pictures/JPEG_20190123_093246_-1720015105.jpg
        Log.i("onActivityResult", "mCurrentPhotoPath: cameraActivity" + mCurrentPhotoPath);

        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

}