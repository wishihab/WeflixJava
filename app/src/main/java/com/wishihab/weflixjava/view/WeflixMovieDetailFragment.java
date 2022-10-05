package com.wishihab.weflixjava.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.databinding.FragmentWeflixDetailBinding;
import com.wishihab.weflixjava.model.general.MovieDetailResponse;
import com.wishihab.weflixjava.util.core.ImageUtil;
import com.wishihab.weflixjava.view.movie.MovieDetailView;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeflixMovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeflixMovieDetailFragment extends Fragment implements MovieDetailView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentWeflixDetailBinding binding;
    private WeflixViewModel weflixViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeflixMovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeflixDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeflixMovieDetailFragment newInstance(String param1, String param2) {
        WeflixMovieDetailFragment fragment = new WeflixMovieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentWeflixDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.switcher.setDisplayedChild(1);
        binding.upButton.setOnClickListener(v -> requireActivity().finish());

        binding.swipeRefresh.setOnRefreshListener(() -> weflixViewModel.doGetMovieDetail(mParam1));

        ViewModelProvider provider = new ViewModelProvider(this);
        weflixViewModel = provider.get(WeflixViewModelImpl.class);
        weflixViewModel.getMovieDetailViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.doGetMovieDetail(mParam1);
    }

    @Override
    public void showData(MovieDetailResponse data) {
        binding.switcher.setDisplayedChild(0);
        String fullBackdrop = "https://image.tmdb.org/t/p/w500/" + data.getBackdropPath();
        ImageUtil.loadImage(binding.movieBackdrop, fullBackdrop, R.drawable.dummy_poster);
        binding.movieTitle.setText(data.getOriginalTitle());
        binding.releaseDateView.setText(data.getReleaseDate());
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<data.getGenres().length; i++){
            sb.append(data.getGenres()[i].getName() + ", ");
        }
        String allGenre = sb.toString();
        binding.genreView.setText(allGenre);
        String fullVote = String.format("%.1f", data.getVoteAverage())+ " ★";
        binding.ratingView.setText(fullVote);
        binding.overviewView.setText(data.getOverview());
    }

    @Override
    public void showProgress(boolean progress) {
        binding.swipeRefresh.setRefreshing(progress);
    }

    @Override
    public void showMessage(String message) {
        binding.switcher.setDisplayedChild(0);
        Log.e("message ", "value " + message);
    }
}