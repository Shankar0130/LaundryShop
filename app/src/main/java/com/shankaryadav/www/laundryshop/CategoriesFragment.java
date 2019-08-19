package com.shankaryadav.www.laundryshop;


import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.shankaryadav.www.laundryshop.Adapter.CategoriesNavAdapter;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    CategoriesNavAdapter categoriesNavAdapter;
    Activity activity;

    ArrayList<Fragment> arrayList = new ArrayList<> ();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate (R.layout.fragment_categories, container, false);

        activity = getActivity ();

        tabLayout = view.findViewById (R.id.cate_tabLayout);
        viewPager = view.findViewById (R.id.cate_viewPager);

        arrayList.add (new WashFold ());
        arrayList.add (new WashIron ());
        arrayList.add (new DryClean ());

        tabLayout.addTab(tabLayout.newTab().setText("Wash & Fold"));
        tabLayout.addTab(tabLayout.newTab().setText("Wash & Iron"));
        tabLayout.addTab(tabLayout.newTab().setText("Dry & Clean"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


       categoriesNavAdapter = new CategoriesNavAdapter (getChildFragmentManager (),activity,arrayList);
        viewPager.setAdapter (categoriesNavAdapter);




        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener (new TabLayout.BaseOnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

}
