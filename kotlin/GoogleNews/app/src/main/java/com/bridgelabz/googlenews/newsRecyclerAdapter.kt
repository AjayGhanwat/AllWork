package com.bridgelabz.googlenews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class newsRecyclerAdapter(var context: Context, public var list: ArrayList<dataModel>) : RecyclerView.Adapter<newsRecyclerAdapter.viewHolder>() {
    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.title.text = list.get(position).title
        holder.desc.text = list.get(position).description
        Picasso.with(context).load(list.get(position).urlToImage).into(holder.image)
        holder.newsTime.text = list.get(position).publishedAt
        holder.cardNews.setOnClickListener(View.OnClickListener {

            var intent = Intent(context, FullNews::class.java)
            intent.putExtra("url", list.get(position).url)
            startActivity(context, intent, null)
        })

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return viewHolder(view)
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.newsTitle)
        var desc: TextView = itemView.findViewById(R.id.newsDesc)
        var image: ImageView = itemView.findViewById(R.id.ImageView)
        var newsTime: TextView = itemView.findViewById(R.id.newsTimeDate)
        var cardNews: CardView = itemView.findViewById(R.id.newsCard)

    }

}