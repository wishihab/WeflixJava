package com.wishihab.weflixjava.adapter.general.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.adapter.core.AdapterListener;
import com.wishihab.weflixjava.databinding.ListItemTabBinding;
import com.wishihab.weflixjava.databinding.MoviePopularListItemBinding;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.util.core.ImageUtil;

import java.util.List;

public class MovieTabListAdapter extends RecyclerView.Adapter<MovieTabListAdapter.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final List<MoviePopularResult> movies;
    private final AdapterListener<MoviePopularResult> listener;

    public MovieTabListAdapter(List<MoviePopularResult> movies, AdapterListener<MoviePopularResult> listener){
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemTabBinding binding = ListItemTabBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){

        }
        MoviePopularResult item = movies.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){ return movies.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ListItemTabBinding binding;

        public ViewHolder(@NonNull ListItemTabBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                MoviePopularResult item = movies.get(pos);
                listener.onItemClick(item, pos);
            });
        }

        public void bind(MoviePopularResult item){
            ImageUtil.loadImage(binding.imageView, item.getPosterPath(), R.drawable.dummy_poster);
            binding.titleView.setText(item.getOriginalTitle());
            binding.infoView.setText(item.getReleaseDate());
            String fullVote = String.valueOf(item.getVoteAverage())+ " ★";
            binding.ratingView.setText(fullVote);
        }
    }
}
