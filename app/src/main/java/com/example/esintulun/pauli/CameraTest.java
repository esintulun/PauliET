package com.example.esintulun.pauli;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraTest extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private static final int MY_PERMISSIONS_CAMERA= 1;
    String m_curentDateandTime;
    String m_imagePath;
   // private static final int MY_PERMISSIONS_READ_EXTERNAL= 1;

    String mCurrentPhotoPath;
    File photoFile = null;
    ImageView ivPhoto;


    private static final String EXTRA_FILENAME = "com.commonsware.android.camcon.EXTRA_FILENAME";
    private static final String FILENAME = "CameraContentDemo.jpeg";
    private static final int CONTENT_REQUEST = 1337;
    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
    private static final String PHOTOS = "photos";
    private File output = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);
        ivPhoto = findViewById(R.id.ivPhototest);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.i("permission....", "Permission is not granted");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_CAMERA);
                Log.i("permission....", "     !!!!!    Request Permission !!!!! ");
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        //
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {

            Log.i("checkper..", "ok .. ");
        }
        else {

            Log.i("checkper... ", " no  .. ");
        }

        if (savedInstanceState == null) {
            output = new File(new File(getFilesDir(), PHOTOS), FILENAME);

            if (output.exists()) {
                output.delete();
            } else {
                output.getParentFile().mkdirs();
            }
            try {
                 photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           // Log.i("onActivityResult", "Authority:..."+ AUTHORITY + " output:.. " + output);

            Uri outputUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);

            //intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //Marschmallow, Oreo
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // Lollipop, M, O
                ClipData clip = ClipData.newUri(getContentResolver(), "A photo", outputUri);
                intent.setClipData(clip);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                List<ResolveInfo> resInfoList =
                        getPackageManager()
                                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, outputUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }

            try {
                startActivityForResult(intent, CONTENT_REQUEST);


            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "no camera !", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            output = (File) savedInstanceState.getSerializable(EXTRA_FILENAME);
        }
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
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.i("path:", "mCurrentPhotoPath" + mCurrentPhotoPath);

        return image;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(EXTRA_FILENAME, output);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        Log.i("onActivityResult", "onActivityResult");
        Log.i("onActivityResult", "Intent getdata: ; " + data.getExtras().get("data")); // content://media/external/images/media/6208

        if (requestCode == CONTENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                //Intent i = new Intent(Intent.ACTION_VIEW);
                //Uri outputUri = FileProvider.getUriForFile(this, AUTHORITY, output);
                Uri outputUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);

                //i.setDataAndType(outputUri, "image/jpeg");
                //i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    //startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "no viewer ", Toast.LENGTH_LONG).show();
                }

                //finish();

                Bundle extras = data.getExtras();
                Log.i("onactivity", "ja.. " +  extras.toString());

                Bitmap imageBitmap = (Bitmap) extras.get("data");

                ivPhoto.setImageBitmap(imageBitmap);
                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

                // Show Uri path based on Image
                Toast.makeText(this,"Here "+ tempUri, Toast.LENGTH_LONG).show();

                // Show Uri path based on Cursor Content Resolver
                Toast.makeText(this, "Real path for URI : "+getRealPathFromURI(tempUri), Toast.LENGTH_SHORT).show();

            }


        }

    }


    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "Title", null);
        Log.i("camera: ", " getImageUri " + path);


        return Uri.parse(path);
    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {

        Log.i("check: ", " drin ");
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        Log.i("checkedresult: ", "onRequestPermissionsResult: ok --  " + requestCode);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                    Log.i("checkedresult: ", "onRequestPermissionsResult: ok ");
                } else {
                    Toast.makeText(this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                    Log.i("checkedresult: ", "onRequestPermissionsResult: no no no  ");

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }*/

}
