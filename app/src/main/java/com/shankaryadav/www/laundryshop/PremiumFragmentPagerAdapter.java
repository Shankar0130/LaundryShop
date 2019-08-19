package com.shankaryadav.www.laundryshop;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PremiumFragmentPagerAdapter extends FragmentStatePagerAdapter implements PremiumCardAdapter {

  private List<PremiumCardFragment> mFragments;
  private float mBaseElevation;

    public PremiumFragmentPagerAdapter(FragmentManager fm, float baseElevation) {
        super (fm);

        mFragments = new ArrayList<> ();
        mBaseElevation = baseElevation;

        for (int i =0; i<3;i++){
            addPremiumFragment (new PremiumCardFragment ());
        }

    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get (position);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int pos) {
        return mFragments.get (pos).getCardView ();
    }

    @Override
    public int getCount() {
        return mFragments.size ();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object fragment = super.instantiateItem (container,position);
        mFragments.set (position,(PremiumCardFragment) fragment);
        return fragment;
    }

    public void addPremiumFragment(PremiumCardFragment fragment){
        mFragments.add (fragment);
    }
}
