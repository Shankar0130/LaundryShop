package com.shankaryadav.www.laundryshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class PremiumCardFragment extends Fragment {

    private CardView mCardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.premium_fragment,container,false);

        mCardView = (CardView) view.findViewById(R.id.cardView);
       mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * PremiumCardAdapter.MAX_ELEVATION_FACTOR);

        return view;
    }


    public CardView getCardView(){
        return mCardView;
    }

}
