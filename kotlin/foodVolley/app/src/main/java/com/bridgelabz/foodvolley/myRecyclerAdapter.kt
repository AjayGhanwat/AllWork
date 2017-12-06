package com.bridgelabz.foodvolley

import android.content.Context
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class myRecyclerAdapter(var context : Context,var list : ArrayList<dataModel>) : RecyclerView.Adapter<myRecyclerAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): viewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.food_row, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.name.text = list.get(position).name
        Picasso.with(context).load(list.get(position).imagelink).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.foodName);
        val image : ImageView = itemView.findViewById(R.id.foodImage);

    }

}