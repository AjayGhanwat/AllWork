package com.bridgelabz.googlemapdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>{

    Context context;
    ArrayList<Datamodel> list;

    public RecyclerViewAdapter(Context context,ArrayList<Datamodel> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.viewHolder holder, int position) {

        holder.locationName.setText(list.get(position).getLocationName());
        holder.locationAddress.setText(list.get(position).getVicinity());
        if (list.get(position).getRating() != null)
        holder.ratingLocation.setRating(Float.parseFloat(list.get(position).getRating()));
        Picasso.with(context).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+ list.get(position).getPreference() +"&key=AIzaSyDPG_iW97-O3hMDM4a9Heb0q6gvuEa9Fmo").placeholder(R.drawable.ic_broken_image_black_24dp).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView locationName, locationAddress;
        RatingBar ratingLocation;
        ImageView imageView;


        public viewHolder(View itemView) {
            super(itemView);

            locationName = (TextView) itemView.findViewById(R.id.nameLocation);
            locationAddress = (TextView) itemView.findViewById(R.id.addressLocation);
            ratingLocation = (RatingBar) itemView.findViewById(R.id.ratingLocation);
            imageView  = (ImageView) itemView.findViewById(R.id.imageLocation);
        }
    }
}
