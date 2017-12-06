package com.bridgelabz.retriverdata;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.UserViewHolder> {

    private List<UserModul> list;

    public UserViewAdapter(List<UserModul> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        UserModul user = list.get(position);
        holder.title.setText(user.mTitle);
        holder.desc.setText(user.mDesc);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add(holder.getAdapterPosition(), 0, 0, "Title");
                menu.add(holder.getAdapterPosition(), 1, 0, "Desc");

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView title,desc;

        public UserViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.noteTitle);
            desc = (TextView) itemView.findViewById(R.id.noteDesc);
        }
    }
}
