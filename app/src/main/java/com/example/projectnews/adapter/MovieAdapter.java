package com.example.projectnews.adapter;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.MovieDetailActivity;
import com.example.projectnews.R;
import com.example.projectnews.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }
    public void setData(ArrayList<Movie> list){
        this.movieArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_new_item, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Movie movieObj = movieArrayList.get(position);
        holder.titleTV.setText(movieObj.getTitle());
        holder.subtitleTV.setText(movieObj.getContent());
        Picasso.get().load(movieObj.getImageLink()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("newId", Integer.toString(movieArrayList.get(position).getID()));
                intent.putExtra("newTitle", movieArrayList.get(position).getTitle());
                intent.putExtra("newCategory", movieArrayList.get(position).getCategory());
                intent.putExtra("newContent", movieArrayList.get(position).getContent());
                intent.putExtra("newImageLink", movieArrayList.get(position).getImageLink());
                intent.putExtra("newAuthor", movieArrayList.get(position).getAuthor());
                intent.putExtra("newCreateDate", movieArrayList.get(position).getCreateDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTV, subtitleTV;
        ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV = itemView.findViewById(R.id.idTVSubTitle);
            newsIV = itemView.findViewById(R.id.idIViews);
        }
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movieArrayList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.listMovie);
        nameTextView.setText(movie.getContent());
        return convertView;
    }
}

