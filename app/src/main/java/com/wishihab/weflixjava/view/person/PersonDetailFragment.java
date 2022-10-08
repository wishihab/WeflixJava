package com.wishihab.weflixjava.view.person;

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
import com.wishihab.weflixjava.databinding.FragmentPersonDetailBinding;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailResult;
import com.wishihab.weflixjava.util.core.ImageUtil;
import com.wishihab.weflixjava.viewmodel.WeflixViewModel;
import com.wishihab.weflixjava.viewmodel.WeflixViewModelImpl;

public class PersonDetailFragment extends Fragment implements PersonDetailView{

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private FragmentPersonDetailBinding binding;
    private WeflixViewModel viewModel;

    public static PersonDetailFragment newInstance(String param1) {
        PersonDetailFragment fragment = new PersonDetailFragment();
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
        binding = FragmentPersonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.upButton.setOnClickListener(v -> requireActivity().finish());

        ViewModelProvider provider = new ViewModelProvider(this);
        viewModel = provider.get(WeflixViewModelImpl.class);
        viewModel.getPersonDetailViewstate().observe(getViewLifecycleOwner(), this::apply);
        viewModel.doGetPersonDetail(mParam1);
    }

    @Override
    public void showDataPersonDetail(PersonDetailResult data) {
        String fullProfile = "https://image.tmdb.org/t/p/w500/" + data.getProfilePath();
        ImageUtil.loadImage(binding.imageView, fullProfile, R.drawable.dummy_poster);
        binding.textView.setText(data.getName());
        binding.birthView.setText(data.getBirthDay());
        binding.biographyView.setText(data.getBiography());
    }

    @Override
    public void showProgressPersonDetail(boolean progress) {
        binding.swipeRefresh.setRefreshing(progress);
    }

    @Override
    public void showMessagePersonDetail(String message) {

    }
}