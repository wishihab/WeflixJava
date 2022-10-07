package com.wishihab.weflixjava.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.wishihab.weflixjava.adapter.core.ViewPagerAdapterList;
import com.wishihab.weflixjava.databinding.FragmentWeflixListMainBinding;
import com.wishihab.weflixjava.view.movie.MovieListTabFragment;
import com.wishihab.weflixjava.view.person.PersonListTabFragment;
import com.wishihab.weflixjava.view.tv.TvListTabFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeflixListMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeflixListMainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentWeflixListMainBinding binding;
    private MovieListTabFragment movieListTabFragment;
    private TvListTabFragment tvListTabFragment;
    private PersonListTabFragment personListTabFragment;
    private ViewPagerAdapterList myAdapter;

    public WeflixListMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeflixListMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeflixListMainFragment newInstance(String param1, String param2) {
        WeflixListMainFragment fragment = new WeflixListMainFragment();
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
        binding = FragmentWeflixListMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieListTabFragment = new MovieListTabFragment();
        tvListTabFragment = new TvListTabFragment();
        personListTabFragment = new PersonListTabFragment();

        //create viewpager adapter inner classnya sini
        myAdapter = new ViewPagerAdapterList(getParentFragmentManager(), getLifecycle());
        myAdapter.addFragment(movieListTabFragment);
        myAdapter.addFragment(tvListTabFragment);
        myAdapter.addFragment(personListTabFragment);

        binding.viewPagerList.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewPagerList.setAdapter(myAdapter);

        binding.tabLayoutList.addTab(binding.tabLayoutList.newTab().setText("Movies"));
        binding.tabLayoutList.addTab(binding.tabLayoutList.newTab().setText("Tv"));
        binding.tabLayoutList.addTab(binding.tabLayoutList.newTab().setText("Person"));
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

        Log.e("mparam 1", " value" + mParam1);
        if(mParam1.equals("movies")){
            binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(0));
        }else if(mParam1.equals("tv")){
            binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(1));
        }else{
            binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(2));
        }
    }
}