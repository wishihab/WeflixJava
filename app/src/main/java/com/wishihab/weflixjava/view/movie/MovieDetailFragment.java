package com.wishihab.weflixjava.view.movie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.YoutubePlayerActivity;
import com.wishihab.weflixjava.adapter.general.movie.MovieReviewListAdapter;
import com.wishihab.weflixjava.databinding.FragmentWeflixDetailBinding;
import com.wishihab.weflixjava.model.general.YoutubeQueryResult;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.util.core.ImageUtil;
import com.wishihab.weflixjava.view.youtube.YoutubeModuleFragment;
import com.wishihab.weflixjava.view.youtube.YoutubeQueryView;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

import java.util.List;

public class MovieDetailFragment extends Fragment implements MovieDetailView, MovieReviewView, YoutubeQueryView {

    private static final String ARG_YOUTUBE_ID = "youtube_id";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentWeflixDetailBinding binding;
    private WeflixViewModel weflixViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static MovieDetailFragment newInstance(String param1, String param2) {
        MovieDetailFragment fragment = new MovieDetailFragment();
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
        binding.userReviewList.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));

        ViewModelProvider provider = new ViewModelProvider(this);
        weflixViewModel = provider.get(WeflixViewModelImpl.class);
        weflixViewModel.getMovieDetailViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.getMovieReviewViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.getYoutubeIdViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.doGetMovieDetail(mParam1);
        weflixViewModel.doGetMovieReview(mParam1);
        String nameTrailer = "Trailer " + mParam2;
        weflixViewModel.doGetYoutubeId(nameTrailer, "AIzaSyBNggAqYJVmKn-p7D_DrlWyX3Rc24fv0uI");

    }


    //Movie Detail
    @Override
    public void showData(MovieDetailResponse data) {
        binding.switcher.setDisplayedChild(0);
        String fullBackdrop = "https://image.tmdb.org/t/p/w500/" + data.getBackdropPath();
        ImageUtil.loadImage(binding.movieBackdrop, fullBackdrop, R.drawable.dummy_poster);
        binding.movieTitle.setText(data.getOriginalTitle());
        binding.releaseDateView.setText(data.getReleaseDate());
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<data.getGenres().length; i++){
            sb.append(data.getGenres()[i].getName() + " ");
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        binding.switcher.setDisplayedChild(0);
    }


    //Review
    @Override
    public void showDataReview(List<MovieReviewResult> data) {
        binding.switcher.setDisplayedChild(0);
        MovieReviewListAdapter movieReviewListAdapter = new MovieReviewListAdapter(data, ((list, position) -> {
            //do onclick detail here?
        }));
        binding.userReviewList.setAdapter(movieReviewListAdapter);
    }

    @Override
    public void showProgressReview(boolean progress) {
        binding.switcher.setDisplayedChild(0);
    }

    @Override
    public void showMessageReview(String message) {
        binding.switcher.setDisplayedChild(0);
    }


    //Youtube
    @Override
    public void showDataYoutube(List<YoutubeQueryResult> data) {
        if(data.size() > 0){
            //opsi 1 load webview
            binding.movieBackdrop.setOnClickListener(v->openWebView(data.get(0).getVideoId()));
            binding.youtubePlayButton.setOnClickListener(v->openWebView(data.get(0).getVideoId()));
            //opsi 2 load buttonmodule?
            //binding.youtubePlayButton.setOnClickListener(v-> onYoutubeClicked(data.get(0).getVideoId()));
            //opsi 3 load activity/fragment youtube?
            //binding.youtubePlayButton.setOnClickListener(v-> initActivityYoutube(data.get(0).getVideoId()));
        }
    }

    @Override
    public void showProgressYoutube(boolean progress) {

    }

    @Override
    public void showMessageYoutube(String message) {

    }


    private void openWebView(String youtubeId){
        WebView youtubeWeb = (WebView) getView().findViewById(R.id.webview_youtube);
        String fullUrl = "https://www.youtube.com/watch?v=" + youtubeId;
        youtubeWeb.loadUrl(fullUrl);
    }

    private void onYoutubeClicked(String youtubeId){
        FragmentManager manager = getParentFragmentManager();
        YoutubeModuleFragment fragment = (YoutubeModuleFragment) manager.findFragmentByTag("youtube_module_tag");
        if(fragment == null){
            fragment = YoutubeModuleFragment.newInstance(youtubeId);
            fragment.show(manager, "youtube_module_tag");
        }
    }

    private void initActivityYoutube(String youtubeId) {
        Intent intent = YoutubePlayerActivity.newIntent(requireActivity());
        intent.putExtra(ARG_YOUTUBE_ID, youtubeId);
        startActivity(intent);
    }
}