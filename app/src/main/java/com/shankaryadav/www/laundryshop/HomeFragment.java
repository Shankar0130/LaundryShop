package com.shankaryadav.www.laundryshop;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.shankaryadav.www.laundryshop.Adapter.HomeRecyAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {

    ViewFlipper viewFlipper;

    Activity activity;

    RecyclerView recyclerView;

    List<HomeRecyPojo> list = new ArrayList<> ();

    HomeRecyAdapter homeRecyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity ();
        View view =  inflater.inflate (R.layout.fragment_home, container, false);


        recyclerView = view.findViewById (R.id.homeRecy);

        list.add (new HomeRecyPojo (R.drawable.india_rupee_currency_symbol,R.string.charges,R.drawable.home_back));
        list.add (new HomeRecyPojo (R.drawable.premium,R.string.premium,R.drawable.home_back));
        list.add (new HomeRecyPojo (R.drawable.purchase,R.string.place_order,R.drawable.home_back));
        list.add (new HomeRecyPojo (R.drawable.refer_copy,R.string.refer_amp_earn,R.drawable.home_back));

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(activity);



        recyclerView.setLayoutManager (layoutManager);

        homeRecyAdapter = new HomeRecyAdapter (list,activity);




//        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
//
//        recyclerView.addItemDecoration (decoration);

        recyclerView.setAdapter (homeRecyAdapter);


        viewFlipper = view.findViewById (R.id.home_viewflipper);

        int[] img = {R.drawable.laundry_banner_one,R.drawable.laundry_flipper,R.drawable.laundry_service_three};

        for (int image : img){
            flipper (image);
        }

        return  view;
    }

    private void flipper(int imgnum) {

        ImageView imageView = new ImageView (activity);
        imageView.setScaleType (ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource (imgnum);

        viewFlipper.addView (imageView);
        viewFlipper.setFlipInterval (2000);

        viewFlipper.setAutoStart (true);

    }

}
