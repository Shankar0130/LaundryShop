package com.shankaryadav.www.laundryshop;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shankaryadav.www.laundryshop.Adapter.ClothsTypeAdapter;

import java.util.ArrayList;
import java.util.List;


public class KidsFragment extends Fragment {


//    String[] name = {
//            "Shirt",
//            "T-shirt",
//            "Shorts",
//            "Night suit",
//            "Frocks",
//            "Jackets",
//            "Blazer",
//    };
//
//
//
//    String[] price = {
//            "30",
//            "25",
//            "20",
//            "25",
//            "25",
//            "50",
//            "70",
//
//    };
    RecyclerView recyclerView;
    private List<Cloths_order_pojo> list = new ArrayList<> ();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_kids, container, false);

        recyclerView = view.findViewById (R.id.kids_recy);

         DatabaseReference databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("Laundry").child ("cloths").child ("kids");

//
//        for (int i = 0; i<name.length;i++){
//            list.add (new Cloths_order_pojo (name[i],price[i]));
//        }



        databaseReference
                .addValueEventListener (new ValueEventListener () {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren ()){
                            Cloths_order_pojo clothsOrderPojo = dataSnapshot1.getValue (Cloths_order_pojo.class);

                            list.add (clothsOrderPojo);
                           // Toast.makeText (getActivity (), ""+clothsOrderPojo.cloths_name, Toast.LENGTH_SHORT).show ();
                        }


                        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));

                        recyclerView.setAdapter (new ClothsTypeAdapter (list,getActivity ()));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return  view;
    }

}
