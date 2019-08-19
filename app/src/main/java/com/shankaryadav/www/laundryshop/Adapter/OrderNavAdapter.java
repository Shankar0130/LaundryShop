package com.shankaryadav.www.laundryshop.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class OrderNavAdapter extends FragmentPagerAdapter {
    private Context mycontext;
    private ArrayList<Fragment> arrayList;

    public OrderNavAdapter(FragmentManager fm, Context mycontext, ArrayList<Fragment> arrayList) {
        super (fm);
        this.mycontext = mycontext;
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.arrayList.get (position);

    }

    @Override
    public int getCount() {
        return arrayList.size ();
    }

}