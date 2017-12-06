package com.bridgelabz.cropper.view;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bridgelabz.cropper.R;
import com.bridgelabz.cropper.adapter.ImageAdapter;
import com.bridgelabz.cropper.model.ImageData;

import java.io.File;
import java.util.ArrayList;

public class MainUserPalet extends Fragment implements View.OnClickListener {

    View v;

    FloatingActionButton open, openGallery, openCamera;

    Animation FabOpen, FabClose, rotateClockwise, rotateAnticlockwise;

    boolean isOpen = false;

    RecyclerView mImageRecyclerView;
    ImageAdapter adapter;

    ArrayList<ImageData> imageDataArrayList;

    File file;

    public static MainUserPalet newInstance() {

        Bundle args = new Bundle();

        MainUserPalet fragment = new MainUserPalet();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_user_palet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Cropper");
        }

        initView();
        imageDataArrayList = FetchImages();
        adapter = new ImageAdapter(getActivity(), imageDataArrayList);
        mImageRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        imageDataArrayList = FetchImages();
        adapter = new ImageAdapter(getActivity(), imageDataArrayList);
        mImageRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {

        mImageRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerviewImage);
        mImageRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mImageRecyclerView.setHasFixedSize(true);

        imageDataArrayList = new ArrayList<>();

        open = (FloatingActionButton) v.findViewById(R.id.open);
        open.setOnClickListener(this);

        openGallery = (FloatingActionButton) v.findViewById(R.id.openGallery);
        openCamera = (FloatingActionButton) v.findViewById(R.id.openCamera);

        FabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.floating_button_animation_open);
        FabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.floating_button_animation_close);
        rotateClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        rotateAnticlockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anticlockwise);

    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.open) {
            if (isOpen) {
                openGallery.startAnimation(FabClose);
                openCamera.startAnimation(FabClose);
                open.startAnimation(rotateAnticlockwise);
                openCamera.setClickable(false);
                openGallery.setClickable(false);
                isOpen = false;
            } else {
                openGallery.startAnimation(FabOpen);
                openCamera.startAnimation(FabOpen);
                open.startAnimation(rotateClockwise);
                openCamera.setClickable(true);
                openGallery.setClickable(true);
                isOpen = true;
            }
        }
    }

    private ArrayList<ImageData> FetchImages() {

        ArrayList<ImageData> list = new ArrayList<>();

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getActivity(), "Error! No SDCARD Found!", Toast.LENGTH_LONG).show();
        } else {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "EditedImages");
            file.mkdirs();
        }

        if (file.isDirectory()) {
            File[] listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++) {
                String fileNameStrings = listFile[i].getName();
                String filePathStrings = listFile[i].getAbsolutePath();

                ImageData imageData = new ImageData(filePathStrings, fileNameStrings);

                list.add(imageData);
            }
        }
        return list;
    }
}
