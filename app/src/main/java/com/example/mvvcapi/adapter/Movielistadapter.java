package com.example.mvvcapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvcapi.R;
import com.example.mvvcapi.model.Moviemodel;

import java.util.List;

public class Movielistadapter extends RecyclerView.Adapter<Movielistadapter.MyViewHolder> {

    private Context context;
    private List<Moviemodel> movielist;
    private ItemClickListener clickListener;


    public Movielistadapter(Context context, List<Moviemodel> movielist, ItemClickListener clickListener) {
        this.context = context;
        this.movielist = movielist;
        this.clickListener = clickListener;
    }

    //not passing the data from the constructor use it explicitly
    public void setMovielist(List<Moviemodel> movielist){
        this.movielist = movielist;
        notifyDataSetChanged(); //this functions always refresh the listview or the recycler view

    }

    @NonNull
    @Override
    public Movielistadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Movielistadapter.MyViewHolder holder, int position) {
        holder.tvtitle.setText(this.movielist.get(position).getName().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMovieClick(movielist.get(position));
            }
        });
        Glide.with(context).load(this.movielist.get(position).getAvatar()).into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        if(this.movielist != null){
            return this.movielist.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvtitle;
        ImageView imageview;

        public MyViewHolder(View itemview) {
            super (itemview);
           tvtitle= itemview.findViewById(R.id.titelview);
           imageview= itemview.findViewById(R.id.imageview);

        }
    }

    public interface ItemClickListener{
        public void onMovieClick(Moviemodel movie);
    }

}
