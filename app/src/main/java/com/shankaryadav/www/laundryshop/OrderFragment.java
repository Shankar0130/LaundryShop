package com.shankaryadav.www.laundryshop;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.shankaryadav.www.laundryshop.Adapter.OrderNavAdapter;

import java.util.ArrayList;


public class OrderFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    private OrderNavAdapter orderNavAdapter;
    private Activity activity;

    ArrayList<Fragment> arrayList = new ArrayList<> ();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate (R.layout.fragment_order, container, false);

       activity = getActivity ();

       arrayList.add (new OrderComplete ());
       arrayList.add (new OrderPending ());

       tabLayout = view.findViewById (R.id.order_tabLayout);
       viewPager = view.findViewById (R.id.order_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Orders\nDone"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders\nDue"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        orderNavAdapter = new OrderNavAdapter (getChildFragmentManager (),activity,arrayList);
        viewPager.setAdapter (orderNavAdapter);


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

       return  view;
    }

    
}
