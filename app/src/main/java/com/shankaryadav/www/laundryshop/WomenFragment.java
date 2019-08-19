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


/**
 * A simple {@link Fragment} subclass.
 */
public class WomenFragment extends Fragment {


    RecyclerView recyclerView;

    List<Cloths_order_pojo> list = new ArrayList<> ();





//    String[] name = {
//            "Scarf",
//            "Muffler",
//            "Dupatta",
//            "Blouse - plain",
//            "Blouse - work",
//            "Shirt",
//            "Top",
//            "T-shirt",
//            "Kurti",
//            "Trousers",
//            "Slack",
//            "Track pant",
//            "Skirt - small",
//            "Skirt - large",
//            "Choli",
//            "Petticoat",
//            "Salwar kameez - plain",
//            "Salwar kameez - work",
//            "Saree - plain",
//            "Saree- silk",
//            "Saree - work",
//            "Ghagra-plain",
//            "Ghagra-work",
//            "Dress/gown",
//            "Track suit",
//            "Shawl - plain",
//            "Shawl - work",
//            "Night-gown",
//            "Overcoat",
//    };
//
//
//
//    String[] price = {
//            "35",
//            "25",
//            "25",
//            "40",
//            "50",
//            "60",
//            "60",
//            "70",
//            "100",
//            "100",
//            "80",
//            "100",
//            "80",
//            "100",
//            "40",
//            "100",
//            "120",
//            "100",
//            "120",
//            "50",
//            "50",
//            "50",
//            "50",
//            "50",
//            "150",
//            "50",
//            "60",
//            "100",
//            "120"
//    };





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View view = inflater.inflate (R.layout.fragment_women, container, false);


        recyclerView = view.findViewById (R.id.women_recy);

//        for (int i = 0; i<name.length;i++){
//            list.add (new Cloths_order_pojo (name[i],price[i]));
//        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("Laundry").child ("cloths").child ("women");


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
