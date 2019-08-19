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
public class Household extends Fragment {

//    String[] name = {
//            "Bedsheet -single",
//      "Bedsheet - double",
//      "Pillow cover -plain",
//      "Bath towel",
//      "Hand towel/table mat",
//      "Table cloth - single",
//      "Table cloth - double",
//      "Sofa cover - single",
//      "Sofa cover - double",
//      "Curtain - small",
//      "Curtain - large",
//      "Curtain - extra large",
//      "Bath robe",
//      "Duvet cover - small",
//      "Duvet cover - large",
//      "Bed spread - single",
//      "Bed spread - double",
//      "Blanket - single",
//      "Blanket - double",
//      "Dari",
//      "Carpet - small",
//      "Carpet - large",
//      "Carpet - large",
//      "Carpet - extra large",
//    };
//
//
//    String[] price = {
//            "100",
//            "160",
//            "35",
//            "40",
//            "50",
//            "40",
//            "60",
//            "60",
//            "100",
//            "40",
//            "60",
//            "80",
//            "40",
//            "50",
//            "60",
//            "50",
//            "60",
//            "100",
//            "150",
//            "50",
//            "200",
//            "250",
//            "250",
//            "300",
//    };


    RecyclerView recyclerView;
    private List<Cloths_order_pojo> list = new ArrayList<> ();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_household, container, false);

        recyclerView = view.findViewById (R.id.hhold_recy);

         DatabaseReference databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("Laundry").child ("cloths").child ("household");


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
