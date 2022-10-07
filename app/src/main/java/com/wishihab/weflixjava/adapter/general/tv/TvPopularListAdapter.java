package com.wishihab.weflixjava.adapter.general.tv;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.adapter.core.AdapterListener;
import com.wishihab.weflixjava.databinding.MoviePopularListItemBinding;
import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.util.core.ImageUtil;

import java.util.List;

public class TvPopularListAdapter extends RecyclerView.Adapter<TvPopularListAdapter.ViewHolder> {

    private final List<TvPopularResult> tvs;
    private final AdapterListener<TvPopularResult> listener;

    public TvPopularListAdapter(List<TvPopularResult> tvs, AdapterListener<TvPopularResult> listener){
        this.tvs = tvs;
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
        TvPopularResult item = tvs.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){ return tvs.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MoviePopularListItemBinding binding;

        public ViewHolder(@NonNull MoviePopularListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                TvPopularResult item = tvs.get(pos);
                listener.onItemClick(item, pos);
            });
        }

        public void bind(TvPopularResult item){
            ImageUtil.loadImage(binding.posterPlaceholder, item.getPosterPath(), R.drawable.dummy_poster);
            String fullVote = String.valueOf(item.getVoteAverage())+ " ★";
            binding.ratePlaceholder.setText(fullVote);
        }

    }
}
