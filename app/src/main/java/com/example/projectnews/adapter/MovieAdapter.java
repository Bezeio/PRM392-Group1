package com.example.projectnews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.MovieDetailActivity;
import com.example.projectnews.R;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.fragtwo;
import com.example.projectnews.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    ArrayList<Movie> movieArrayList;
    public DBHelper helper;
    public Boolean canAddToCart = false;
    public IMovieAdapter adapter;
    private Fragment contextGetter;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    public void setData(ArrayList<Movie> list) {
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
        if (canAddToCart) holder.add.setVisibility(View.VISIBLE);
        else holder.add.setVisibility(View.GONE);
        holder.add.setOnClickListener(v -> {
            if (helper != null) {
                if (helper.addToCart(movieObj.getID())) {
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                    if (adapter != null) adapter.update();
                } else
                    Toast.makeText(context, "This movie has already add to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, fragtwo.class);
                intent.putExtra("newId", Integer.toString(movieArrayList.get(position).getID()));
                intent.putExtra("newTitle", movieArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("newId", Integer.toString(movieArrayList.get(position).getID()));
            intent.putExtra("newTitle", movieArrayList.get(position).getTitle());
            intent.putExtra("newCategory", movieArrayList.get(position).getCategory());
            intent.putExtra("newContent", movieArrayList.get(position).getContent());
            intent.putExtra("newImageLink", movieArrayList.get(position).getImageLink());
            intent.putExtra("newAuthor", movieArrayList.get(position).getAuthor());
            intent.putExtra("newCreateDate", movieArrayList.get(position).getCreateDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, subtitleTV;
        ImageView newsIV;
        CardView add;
        Button book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV = itemView.findViewById(R.id.idTVSubTitle);
            newsIV = itemView.findViewById(R.id.idIViews);
            add = itemView.findViewById(R.id.add_to_card);
            book = itemView.findViewById(R.id.booking);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movieArrayList.get(position);

//        TextView nameTextView = convertView.findViewById(R.id.listMovie);
//        nameTextView.setText(movie.getContent());
        return convertView;
    }

    public interface IMovieAdapter {
        void update();
    }

}

