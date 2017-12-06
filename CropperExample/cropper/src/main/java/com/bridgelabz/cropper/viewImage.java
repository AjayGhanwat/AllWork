package com.bridgelabz.cropper;

import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class viewImage extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView, back;

    ImageView imageRotate, imageFlip;

    Animation rotate_90;

    String name;
    String location;
    int a = 0;

    Button horizontal,vertical, save;

    Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initView();
        clickListner();
        setImage();
        myBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    private void setImage() {
        Bundle bundle = getIntent().getExtras();

        location = bundle.get("location").toString();
        name = bundle.get("name").toString();

        Bitmap bitmap = BitmapFactory.decodeFile(location);
        imageView.setImageBitmap(bitmap);
    }

    private void initView() {

        imageFlip = findViewById(R.id.imageFlip);
        horizontal = findViewById(R.id.horizontalFlip);
        vertical = findViewById(R.id.verticalFlip);

        save = findViewById(R.id.storeImage);

        rotate_90 = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise_90_degree);

        imageView = findViewById(R.id.imageView);
        back = findViewById(R.id.backToMain);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageRotate = (ImageView) findViewById(R.id.imageRotate);

    }

    private void clickListner() {

        imageRotate.setOnClickListener(this);
        imageFlip.setOnClickListener(this);
        horizontal.setOnClickListener(this);
        vertical.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.imageRotate) {

            imageView.startAnimation(rotate_90);
            if (a >= 360) {
                a = 0;
            }
            myBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            Bitmap rotated = rotate(myBitmap, a);
            imageView.setImageBitmap(rotated);
            a = a + 90;

        } else if (i == R.id.imageFlip){

            if (horizontal.getVisibility() == View.GONE){
                horizontal.setVisibility(View.VISIBLE);
                vertical.setVisibility(View.VISIBLE);
                horizontal.setClickable(true);
                vertical.setClickable(true);
            }else {
                horizontal.setVisibility(View.GONE);
                vertical.setVisibility(View.GONE);
                horizontal.setClickable(false);
                vertical.setClickable(false);
            }

        } else if (i == R.id.storeImage){
            myBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            String extr = Environment.getExternalStorageDirectory() + File.separator + "EditedImages";
            File myPath = new File(extr, name);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(myPath);
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                MediaStore.Images.Media.insertImage(this.getContentResolver(),myBitmap, myPath.getPath(), name);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finish();
        }

        if (i == R.id.horizontalFlip){
            myBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            Bitmap fliped = flip(myBitmap, 2);
            imageView.setImageBitmap(fliped);
            a=0;
        }else if (i == R.id.verticalFlip){
            myBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            Bitmap fliped = flip(myBitmap, 1);
            imageView.setImageBitmap(fliped);
            a=0;
        }

    }

    public static Bitmap rotate(Bitmap src, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    @Nullable
    public static Bitmap flip(Bitmap src, int type) {
        Matrix matrix = new Matrix();
        if(type == 1) {
            matrix.preScale(1.0f, -1.0f);
        }
        else if(type == 2) {
            matrix.preScale(-1.0f, 1.0f);
        } else {
            return null;
        }
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }
}