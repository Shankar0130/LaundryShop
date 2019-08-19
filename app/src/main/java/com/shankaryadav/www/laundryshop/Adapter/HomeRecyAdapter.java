package com.shankaryadav.www.laundryshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shankaryadav.www.laundryshop.ChargesActivity;
import com.shankaryadav.www.laundryshop.HomeRecyPojo;
import com.shankaryadav.www.laundryshop.PlaceOrder;
import com.shankaryadav.www.laundryshop.Premium;
import com.shankaryadav.www.laundryshop.R;
import com.shankaryadav.www.laundryshop.ReferAndEarn;

import java.util.List;

public class HomeRecyAdapter extends RecyclerView.Adapter<HomeRecyAdapter.HomeRecyViewHolder>{

    List<HomeRecyPojo> list;
    Context context;


    public HomeRecyAdapter(List<HomeRecyPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public HomeRecyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());

        View view = inflater.inflate (R.layout.homerecy_item,parent,false);

        return  new HomeRecyViewHolder (view);
    }


    @Override
    public void onBindViewHolder(@NonNull HomeRecyViewHolder holder, int position) {

        holder.textView.setText (list.get (position).name);
        Glide.with (context).load (list.get (position).img).into (holder.imageView);
        holder.imageback.setBackgroundResource (list.get (position).color);


    }


    @Override
    public int getItemCount() {
        return list.size ();
    }





    public  class HomeRecyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageback;
        ImageView imageView;
        TextView textView;
        CardView cardView;


        public HomeRecyViewHolder(@NonNull View itemView) {
            super (itemView);



            imageback = itemView.findViewById (R.id.homerecy_imagebutton);
            imageView = itemView.findViewById (R.id.homerecy_imageview);
            textView = itemView.findViewById (R.id.homerecy_textview);
            cardView = itemView.findViewById (R.id.homerecy_cardview);

//            cardView.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//
//                    int position = getAdapterPosition ();
//
//                    switch (position){
//
//                        case 0:
//                            Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
//                            context.startActivity (new Intent (context,ChargesActivity.class));
//                            break;
//                        case 1:
//                            Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
//                            context.startActivity (new Intent (context,Premium.class));
//                            break;
//                        case 2:
//                            Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
//                            context.startActivity (new Intent (context,PlaceOrder.class));
//                            break;
//                        case 3:
//                            Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
//                            context.startActivity (new Intent (context,ReferAndEarn.class));
//                            break;
//
//                    }
//
//
//                }
//            });


            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {


                    int position = getAdapterPosition ();

                    switch (position){

                        case 0:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context, ChargesActivity.class));
                            break;
                        case 1:
                            // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context, Premium.class));
                            break;
                        case 2:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context, PlaceOrder.class));
                            break;
                        case 3:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context, ReferAndEarn.class));
                            break;

                    }


                }
            });

//
            imageView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition ();

                    switch (position){

                        case 0:
                          //  Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,ChargesActivity.class));
                            break;
                        case 1:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,Premium.class));
                            break;
                        case 2:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,PlaceOrder.class));
                            break;
                        case 3:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,ReferAndEarn.class));
                            break;

                    }


                }
            });





            textView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition ();

                    switch (position){

                        case 0:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,ChargesActivity.class));
                            break;
                        case 1:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,Premium.class));
                            break;
                        case 2:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,PlaceOrder.class));
                            break;
                        case 3:
                           // Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            context.startActivity (new Intent (context,ReferAndEarn.class));
                            break;

                    }


                }
            });



        }

    }



}
