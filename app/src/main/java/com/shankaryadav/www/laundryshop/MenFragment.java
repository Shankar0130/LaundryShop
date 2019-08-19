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
public class MenFragment extends Fragment {

    RecyclerView recyclerView;

    List<Cloths_order_pojo> list = new ArrayList<> ();

//
//
//    String[] name = {
//            "Men’s clothes",
//    "Shirt",
//    "T-shirt",
//            "Trousers",
//    "Jeans",
//     "Kurta",
//     "Waist coat",
//     "Silk dhoti",
//     "Pyjama",
//     "Shorts",
//     "Tie",
//     "Sweater",
//     "Sports jacket",
//     "Safari suit",
//     "Nehru jacket",
//     "Sherwani",
//     "Blazer",
//     "Suit",
//     "Leather jacket",
//     "Muffler",
//    };
//
//
//    String[] price = {
//            "40",
//            "45",
//            "50",
//            "70",
//            "100",
//            "100",
//            "35",
//            "35",
//            "40",
//            "25",
//            "150",
//            "100",
//            "200",
//            "150",
//            "250",
//            "250",
//            "100",
//            "150",
//            "300",
//            "20",
//
//
//
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_men, container, false);

        recyclerView = view.findViewById (R.id.men_recy);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("Laundry").child ("cloths").child ("men");


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

        syncDatabase (databaseReference);

        return  view;

    }


    public void syncDatabase(DatabaseReference mDatabase){
        if (mDatabase == null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            mDatabase = database.getReference();

        }
    }

}
