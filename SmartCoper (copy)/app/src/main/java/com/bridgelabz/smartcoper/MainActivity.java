package com.bridgelabz.smartcoper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    final int requestPermissionCode = 1;
    ImageView imageView;
    Toolbar toolbar;
    File file;
    Uri uri;
    Intent cameraIntent, galIntent, cropIntent;
    DisplayMetrics displayMetrics;
    int wedith, height;
    Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Crop Image");
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.cropedImage);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_DENIED)
            RequestRuntimePermission();
    }

    private void RequestRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            Toast.makeText(this, "Camera permission Allow Us to access Camera App", Toast.LENGTH_SHORT).show();
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestPermissionCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.camera:
                cameraOpen();
                break;
        }

        return true;
    }

    private void cameraOpen() {

        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(), "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        //uri = Uri.fromFile(file);
        photoURI = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", file);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        cameraIntent.putExtra("return-data", true);
        startActivityForResult(cameraIntent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == RESULT_OK) {
            startCrop();
        } else if (requestCode == 1) {
            if (data != null) {
                uri = data.getData();
                Picasso.with(this).load(uri).into(imageView);
            }
        }
    }

    private void startCrop() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoURI, "image/*");
        intent.putExtra("crop", "true");
        //intent.putExtra("outputX", 500);
        //intent.putExtra("outputY", 500);
        //intent.putExtra("aspectX", 5);
        //intent.putExtra("aspectY", 6);
        //intent.putExtra("scaleUpIfNeeded", true);
        //intent.putExtra("scale", "true");
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.name());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case requestPermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                }

        }
    }
}