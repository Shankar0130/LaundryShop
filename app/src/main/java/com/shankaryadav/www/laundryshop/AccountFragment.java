package com.shankaryadav.www.laundryshop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.shankaryadav.www.laundryshop.Adapter.AccountRecyAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private Activity activity;
    private RecyclerView recyclerView;

    List<AccountRecyPojo> list = new ArrayList<> ();

    TextView signout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_account, container, false);
        activity  = getActivity ();

        list.add (new AccountRecyPojo (R.drawable.notification,R.string.notifi));
        list.add (new AccountRecyPojo (R.drawable.track,R.string.morder));
        list.add (new AccountRecyPojo (R.drawable.address,R.string.manaddre));
        list.add (new AccountRecyPojo (R.drawable.purse,R.string.mywall));
        list.add (new AccountRecyPojo (R.drawable.positive_vote,R.string.rateus));
        list.add (new AccountRecyPojo (R.drawable.question,R.string.help));

        recyclerView = view.findViewById (R.id.account_recy);
        signout = view.findViewById (R.id.account_signout_btn);
        recyclerView.setLayoutManager (new LinearLayoutManager (activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity,
                DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter (new AccountRecyAdapter (list,activity));


        signout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance ().signOut ();
                startActivity (new Intent (getActivity (),AuthenticationPage.class));
                getActivity ().finish ();
            }
        });

        return view;
    }

}
