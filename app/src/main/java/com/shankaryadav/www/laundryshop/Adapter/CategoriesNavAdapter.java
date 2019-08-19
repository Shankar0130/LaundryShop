package com.shankaryadav.www.laundryshop.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CategoriesNavAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> _fragments;

    public CategoriesNavAdapter(FragmentManager fm, Context context, ArrayList<Fragment> _fragments) {
        super (fm);
        this.context = context;
        this._fragments = _fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return this._fragments.get(position);

    }

    @Override
    public int getCount() {
        return _fragments.size ();
    }
}
