package com.bridgelabz.cropper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.cropper.R;
import com.bridgelabz.cropper.model.ImageData;
import com.bridgelabz.cropper.viewImage;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.viewHolder> {

    Context context;
    ArrayList<ImageData> list;

    public ImageAdapter(Context context, ArrayList<ImageData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        Bitmap myBitmap = BitmapFactory.decodeFile(list.get(position).getLocation());
        holder.imageView.setImageBitmap(myBitmap);
        holder.ImageName.setText(list.get(position).getImageName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView ImageName;

        public viewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            ImageName = (TextView) itemView.findViewById(R.id.ImageName);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, viewImage.class);
            intent.putExtra("location", list.get(getAdapterPosition()).getLocation());
            intent.putExtra("name", list.get(getAdapterPosition()).getImageName());
            context.startActivity(intent);
        }
    }

}
