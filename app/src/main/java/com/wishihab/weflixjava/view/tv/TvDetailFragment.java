package com.wishihab.weflixjava.view.tv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.databinding.FragmentTvDetailBinding;
import com.wishihab.weflixjava.model.general.tv.detail.TvDetailResult;
import com.wishihab.weflixjava.util.core.ImageUtil;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

public class TvDetailFragment extends Fragment implements TvDetailView{

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private FragmentTvDetailBinding binding;
    private WeflixViewModel viewModel;
    public static TvDetailFragment newInstance(String param1) {
        TvDetailFragment fragment = new TvDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTvDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.upButton.setOnClickListener(v -> requireActivity().finish());

        ViewModelProvider provider = new ViewModelProvider(this);
        viewModel = provider.get(WeflixViewModelImpl.class);
        viewModel.getTvDetailViewState().observe(getViewLifecycleOwner(), this::apply);
        viewModel.doGetTvDetail(mParam1);
    }

    @Override
    public void showDataTvDetail(TvDetailResult data) {
        String fullBack= "https://image.tmdb.org/t/p/w500/" + data.getBackdrop();
        ImageUtil.loadImage(binding.imageView, fullBack, R.drawable.dummy_poster);
        binding.textView.setText(data.getOriginalName());
        binding.birthView.setText(data.getFirstAirDate());
        binding.biographyView.setText(data.getOverview());
    }

    @Override
    public void showProgressTvDetail(boolean progress) {
        binding.swipeRefresh.setRefreshing(progress);
    }

    @Override
    public void showMessageTvDetail(String message) {

    }
}