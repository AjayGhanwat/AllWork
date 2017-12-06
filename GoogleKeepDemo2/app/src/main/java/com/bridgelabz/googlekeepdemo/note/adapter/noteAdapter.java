package com.bridgelabz.googlekeepdemo.note.adapter;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.editNote.editnote;
import com.bridgelabz.googlekeepdemo.note.model.noteData;

import java.util.ArrayList;

public class noteAdapter extends RecyclerView.Adapter<noteAdapter.viewHolder> {

    ArrayList<noteData> list;

    public noteAdapter(ArrayList<noteData> list) {
        this.list = list;
    }

    @Override
    public noteAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(noteAdapter.viewHolder holder, int position) {
        holder.user_Title.setText(list.get(position).getTitle());
        holder.user_desc.setText(list.get(position).getNote());
        holder.imageView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView user_Title;
        TextView user_desc;
        AppCompatImageView imageView;
        CardView card;

        public viewHolder(final View itemView) {
            super(itemView);

            user_Title = (TextView) itemView.findViewById(R.id.titleTextView);
            user_desc = (TextView) itemView.findViewById(R.id.descTextView);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.importantPin);
            card = (CardView) itemView.findViewById(R.id.cardView);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user_title = list.get(getAdapterPosition()).getTitle();
                    String user_desc = list.get(getAdapterPosition()).getNote();
                    String user_id = list.get(getAdapterPosition()).getId();

                    Intent intent = new Intent(itemView.getContext(), editnote.class);
                    intent.putExtra("Title", user_title);
                    intent.putExtra("Desc", user_desc);
                    intent.putExtra("Key", user_id);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
