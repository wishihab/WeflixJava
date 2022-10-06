package com.wishihab.weflixjava.adapter.general;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.adapter.core.AdapterListener;
import com.wishihab.weflixjava.databinding.MoviePopularListItemBinding;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.util.core.ImageUtil;

import java.util.List;

public class MoviePopularListAdapter extends RecyclerView.Adapter<MoviePopularListAdapter.ViewHolder> {

    private final List<MoviePopularResult> movies;
    private final AdapterListener<MoviePopularResult> listener;

    public MoviePopularListAdapter(List<MoviePopularResult> movies, AdapterListener<MoviePopularResult> listener){
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MoviePopularListItemBinding binding = MoviePopularListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoviePopularResult item = movies.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){ return movies.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MoviePopularListItemBinding binding;

        public ViewHolder(@NonNull MoviePopularListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                MoviePopularResult item = movies.get(pos);
                listener.onItemClick(item, pos);
            });
        }

        public void bind(MoviePopularResult item){
            ImageUtil.loadImage(binding.posterPlaceholder, item.getPosterPath(), R.drawable.dummy_poster);
            String fullVote = String.valueOf(item.getVoteAverage())+ " ★";
            binding.ratePlaceholder.setText(fullVote);
        }

    }
}
