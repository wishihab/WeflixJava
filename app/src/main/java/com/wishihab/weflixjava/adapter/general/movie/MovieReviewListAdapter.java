package com.wishihab.weflixjava.adapter.general.movie;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wishihab.weflixjava.adapter.core.AdapterListener;
import com.wishihab.weflixjava.databinding.MovieReviewListItemBinding;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;

import java.util.List;

public class MovieReviewListAdapter extends RecyclerView.Adapter<MovieReviewListAdapter.ViewHolder> {

    private final List<MovieReviewResult> movieReviews;
    private final AdapterListener<MovieReviewResult> listener;

    public MovieReviewListAdapter(List<MovieReviewResult> movieReviews, AdapterListener<MovieReviewResult> listener){
        this.movieReviews = movieReviews;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieReviewListItemBinding binding = MovieReviewListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieReviewResult item = movieReviews.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){ return movieReviews.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MovieReviewListItemBinding binding;

        public ViewHolder(@NonNull MovieReviewListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                MovieReviewResult item = movieReviews.get(pos);
                listener.onItemClick(item, pos);
            });
        }

        public void bind(MovieReviewResult item){
            binding.usernameView.setText(item.getAuthor());
            String fullVote = String.valueOf(item.getRating())+ " ★";
            binding.ratingView.setText(fullVote);
            binding.reviewContent.setText(item.getContent());
        }

    }
}
