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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonDetailFragment extends Fragment implements PersonDetailView{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentPersonDetailBinding binding;
    private WeflixViewModel viewModel;

    public PersonDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonDetailFragment newInstance(String param1, String param2) {
        PersonDetailFragment fragment = new PersonDetailFragment();
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
        Log.e("person" , " id " + mParam1);
        viewModel.doGetPersonDetail(mParam1);
    }

    @Override
    public void showDataPersonDetail(PersonDetailResult data) {
        Log.e("data result " , "data " + data);
        String fullProfile = "https://image.tmdb.org/t/p/w500/" + data.getProfilePath();
        ImageUtil.loadImage(binding.imageView, fullProfile, R.drawable.dummy_poster);
        binding.textView.setText(data.getName());
        binding.birthView.setText(data.getBirthDay());
        binding.biographyView.setText(data.getBiography());
    }

    @Override
    public void showProgressPersonDetail(boolean progress) {

    }

    @Override
    public void showMessagePersonDetail(String message) {

    }
}