package com.wishihab.weflixjava.adapter.general.person;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.adapter.core.AdapterListener;
import com.wishihab.weflixjava.databinding.MoviePopularListItemBinding;
import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.util.core.ImageUtil;

import java.util.List;

public class PersonPopularListAdapter extends RecyclerView.Adapter<PersonPopularListAdapter.ViewHolder> {

    private final List<PersonPopularResult> persons;
    private final AdapterListener<PersonPopularResult> listener;

    public PersonPopularListAdapter(List<PersonPopularResult> tvs, AdapterListener<PersonPopularResult> listener){
        this.persons = tvs;
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
        PersonPopularResult item = persons.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){ return persons.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MoviePopularListItemBinding binding;

        public ViewHolder(@NonNull MoviePopularListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                PersonPopularResult item = persons.get(pos);
                listener.onItemClick(item, pos);
            });
        }

        public void bind(PersonPopularResult item){
            ImageUtil.loadImage(binding.posterPlaceholder, item.getProfilePath(), R.drawable.dummy_poster);
            binding.ratePlaceholder.setText(item.getName());
        }

    }
}
