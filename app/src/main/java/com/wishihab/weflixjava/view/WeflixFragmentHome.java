package com.wishihab.weflixjava.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishihab.weflixjava.WeflixDetailActivity;
import com.wishihab.weflixjava.adapter.MoviePopularListAdapter;
import com.wishihab.weflixjava.adapter.PersonPopularListAdapter;
import com.wishihab.weflixjava.adapter.TvPopularListAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeflixFragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeflixFragmentHome extends Fragment implements MovieView, TvView, PersonView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_MOVIE_ID = "movie_id";
    private static final String ARG_MOVIE_TITLE = "movie_title";

    private FragmentWeflixHomeBinding binding;
    private WeflixViewModel weflixViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeflixFragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeflixFragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static WeflixFragmentHome newInstance(String param1, String param2) {
        WeflixFragmentHome fragment = new WeflixFragmentHome();
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
        MoviePopularListAdapter moviePopularListAdapter = new MoviePopularListAdapter(data, ((list, position) -> {
            initActivityDetail(list.getId(), list.getOriginalTitle());
        }));
        binding.moviePopularList.setAdapter(moviePopularListAdapter);
    }

    @Override
    public void showDataTv(List<TvPopularResult> data) {
        binding.switcher.setDisplayedChild(0);
        binding.tvHandler.setVisibility(View.GONE);
        TvPopularListAdapter tvPopularListAdapter = new TvPopularListAdapter(data, ((list, position) -> {
            //do onclick detail here?
        }));
        binding.tvPopularList.setAdapter(tvPopularListAdapter);
    }

    @Override
    public void showDataPerson(List<PersonPopularResult> data) {
        binding.switcher.setDisplayedChild(0);
        binding.personHandler.setVisibility(View.GONE);
        PersonPopularListAdapter personPopularListAdapter= new PersonPopularListAdapter(data, ((list, position) -> {
            //do onclick detail here?
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

    private void initActivityDetail(String movieId, String movieTitle) {
        Intent intent = WeflixDetailActivity.newIntent(requireActivity());
        intent.putExtra(ARG_MOVIE_ID, movieId);
        intent.putExtra(ARG_MOVIE_TITLE, movieTitle);
        startActivity(intent);
    }
}