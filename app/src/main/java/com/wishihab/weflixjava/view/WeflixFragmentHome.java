package com.wishihab.weflixjava.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.WeflixDetailActivity;
import com.wishihab.weflixjava.WeflixListActivity;
import com.wishihab.weflixjava.adapter.general.movie.MoviePopularListAdapter;
import com.wishihab.weflixjava.adapter.general.person.PersonPopularListAdapter;
import com.wishihab.weflixjava.adapter.general.tv.TvPopularListAdapter;
import com.wishihab.weflixjava.databinding.FragmentWeflixHomeBinding;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.view.movie.MovieView;
import com.wishihab.weflixjava.view.person.PersonView;
import com.wishihab.weflixjava.view.tv.TvView;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

import java.util.List;

public class WeflixFragmentHome extends Fragment implements MovieView, TvView, PersonView {

    private static final String ARG_MOVIE_ID = "movie_id";
    private static final String ARG_MOVIE_TITLE = "movie_title";
    private static final String ARG_DETAIL_TYPE = "detail_type";
    private static final String ARG_PERSON_ID = "person_id";
    private static final String ARG_TV_ID = "tv_id";

    private Integer page = 1;
    private FragmentWeflixHomeBinding binding;
    private WeflixViewModel weflixViewModel;
    private MoviePopularListAdapter moviePopularListAdapter;


    public static WeflixFragmentHome newInstance() {
        WeflixFragmentHome fragment = new WeflixFragmentHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWeflixHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.switcher.setDisplayedChild(1);

        binding.moviePopularList.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.tvPopularList.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.personPopularList.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));

        binding.swipeRefresh.setOnRefreshListener(() -> weflixViewModel.refresh());

        ViewModelProvider provider = new ViewModelProvider(this);
        weflixViewModel = provider.get(WeflixViewModelImpl.class);
        weflixViewModel.getMovieViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.doGetMoviePage(page);
        weflixViewModel.getTvViewState().observe(getViewLifecycleOwner(), this::apply);
        weflixViewModel.getPersonViewState().observe(getViewLifecycleOwner(), this::apply);
    }

    @Override
    public void showProgress(boolean progress) {
        //progress
        binding.swipeRefresh.setRefreshing(progress);
    }

    @Override
    public void showProgressTv(boolean progress) {
        binding.swipeRefresh.setRefreshing(progress);
    }


    @Override
    public void showProgressPerson(boolean progress) {
        binding.swipeRefresh.setRefreshing(progress);
    }


    @Override
    public void showData(List<MoviePopularResult> data) {
        //data
        binding.switcher.setDisplayedChild(0);
        binding.movieHandler.setVisibility(View.GONE);
        moviePopularListAdapter = new MoviePopularListAdapter(data, ((list, position) -> {
            initActivityDetail(getString(R.string.movies), "", list.getId(), list.getOriginalTitle(), "");
        }));
        binding.moviePopularList.setAdapter(moviePopularListAdapter);
        binding.moviePopularAllBtn.setOnClickListener(v -> initActivityList(getString(R.string.movies)));
    }

    @Override
    public void showDataTv(List<TvPopularResult> data) {
        binding.switcher.setDisplayedChild(0);
        binding.tvHandler.setVisibility(View.GONE);
        TvPopularListAdapter tvPopularListAdapter = new TvPopularListAdapter(data, ((list, position) -> {
            initActivityDetail(getString(R.string.tv), "", "", "", list.getId());
        }));
        binding.tvPopularList.setAdapter(tvPopularListAdapter);
    }

    @Override
    public void showDataPerson(List<PersonPopularResult> data) {
        binding.switcher.setDisplayedChild(0);
        binding.personHandler.setVisibility(View.GONE);
        PersonPopularListAdapter personPopularListAdapter= new PersonPopularListAdapter(data, ((list, position) -> {
            initActivityDetail(getString(R.string.person), list.getId(), "", "", "");
        }));
        binding.personPopularList.setAdapter(personPopularListAdapter);
    }


    @Override
    public void showMessage(String message) {
        binding.switcher.setDisplayedChild(0);
        binding.movieHandler.setVisibility(View.VISIBLE);
        //message
    }

    @Override
    public void showMessageTv(String message) {
        binding.switcher.setDisplayedChild(0);
        binding.tvHandler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessagePerson(String message) {
        binding.switcher.setDisplayedChild(0);
        binding.personHandler.setVisibility(View.VISIBLE);
    }

    private void initActivityDetail(String type, String personId, String movieId, String movieTitle, String tvId) {
        Intent intent = WeflixDetailActivity.newIntent(requireActivity());
        intent.putExtra(ARG_DETAIL_TYPE, type);
        intent.putExtra(ARG_PERSON_ID, personId);
        intent.putExtra(ARG_MOVIE_ID, movieId);
        intent.putExtra(ARG_MOVIE_TITLE, movieTitle);
        intent.putExtra(ARG_TV_ID, tvId);
        startActivity(intent);
    }


    private void initActivityList(String type) {
        Intent intent = WeflixListActivity.newIntent(requireActivity());
        intent.putExtra("TYPE_ID", type);
        startActivity(intent);
    }
}