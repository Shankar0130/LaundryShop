package com.shankaryadav.www.laundryshop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WashFold extends Fragment {

Button pobtn;
Button ppbtn;

private Activity activity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate (R.layout.fragment_wash_fold, container, false);
        pobtn = view.findViewById (R.id.washfold_placeorder);
        ppbtn = view.findViewById (R.id.washfold_planpremium);



        activity = getActivity ();


        pobtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (activity,PlaceOrder.class);
                startActivity (intent);
            }
        });


        ppbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (activity,Premium.class);
                startActivity (intent);
            }
        });


        return view;
    }


}
