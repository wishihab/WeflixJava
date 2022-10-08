package com.wishihab.weflixjava.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.adapter.core.ViewPagerAdapterList;
import com.wishihab.weflixjava.databinding.FragmentWeflixListMainBinding;
import com.wishihab.weflixjava.view.movie.MovieListTabFragment;

public class WeflixListMainFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private FragmentWeflixListMainBinding binding;
    private MovieListTabFragment movieListTabFragment;
    private ViewPagerAdapterList myAdapter;

    public static WeflixListMainFragment newInstance(String param1) {
        WeflixListMainFragment fragment = new WeflixListMainFragment();
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
        // Inflate the layout for this fragment
        binding = FragmentWeflixListMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = requireActivity().findViewById(R.id.up_button);
        imageView.setOnClickListener(v-> requireActivity().onBackPressed());

        movieListTabFragment = new MovieListTabFragment();

        //create viewpager adapter inner classnya sini
        myAdapter = new ViewPagerAdapterList(getParentFragmentManager(), getLifecycle());
        myAdapter.addFragment(movieListTabFragment);

        binding.viewPagerList.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewPagerList.setAdapter(myAdapter);

        binding.tabLayoutList.addTab(binding.tabLayoutList.newTab().setText("Movies"));
        binding.tabLayoutList.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPagerList.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPagerList.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(position));
            }
        });

        binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(0));

    }
}