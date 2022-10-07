package com.wishihab.weflixjava.view.movie;

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
import com.wishihab.weflixjava.adapter.general.movie.MovieTabListAdapter;
import com.wishihab.weflixjava.databinding.FragmentMovieListTabBinding;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieListTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieListTabFragment extends Fragment implements MovieView{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_MOVIE_ID = "movie_id";
    private static final String ARG_MOVIE_TITLE = "movie_title";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentMovieListTabBinding binding;
    private WeflixViewModel viewModel;
    private Integer page = 2;
    private boolean isLoading = false;
    private List<MoviePopularResult> dataLocal;
    private MovieTabListAdapter movieTabListAdapter;

    public MovieListTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieListTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieListTabFragment newInstance(String param1, String param2) {
        MovieListTabFragment fragment = new MovieListTabFragment();
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
        binding = FragmentMovieListTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataLocal = new ArrayList<>();

        ViewModelProvider provider = new ViewModelProvider(this);
        viewModel = provider.get(WeflixViewModelImpl.class);
        binding.swipeRefreshView.setOnRefreshListener(() -> viewModel.doGetMoviePage(page));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        viewModel.getMovieViewState().observe(getViewLifecycleOwner(), this::apply);
        viewModel.doGetMoviePage(2);
    }

    @Override
    public void showData(List<MoviePopularResult> data) {
        for(int i=0; i<data.size(); i++){
            dataLocal.add(data.get(i));
        }
        Log.e("size " , " data local " + dataLocal.size());
        movieTabListAdapter = new MovieTabListAdapter(dataLocal, ((list, position) -> {
            initActivityDetail(list.getId(), list.getOriginalTitle());
        }));
        binding.recyclerView.setAdapter(movieTabListAdapter);
        initMovieScrollListener(data);
    }

    @Override
    public void showProgress(boolean progress) {
        binding.swipeRefreshView.setRefreshing(progress);
    }

    @Override
    public void showMessage(String message) {

    }

    private void initMovieScrollListener(List<MoviePopularResult> data){
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerView.getLayoutManager();
                if(!isLoading){
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataLocal.size() -1){
                        isLoading = true;
                        loadMoreMovie(data);
                    }
                }
            }
        });
    }

    private void loadMoreMovie(List<MoviePopularResult> data){
        page++;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("page " , "page " + page);
                viewModel.doGetMoviePage(page);
                isLoading = false;
            }
        }, 2000);
    }

    private void initActivityDetail(String movieId, String movieTitle) {
        Intent intent = WeflixDetailActivity.newIntent(requireActivity());
        intent.putExtra(ARG_MOVIE_ID, movieId);
        intent.putExtra(ARG_MOVIE_TITLE, movieTitle);
        startActivity(intent);
    }
}