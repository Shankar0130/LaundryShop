package com.shankaryadav.www.laundryshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shankaryadav.www.laundryshop.Cloths_order_pojo;
import com.shankaryadav.www.laundryshop.R;

import java.util.List;

public class ClothsTypeAdapter extends RecyclerView.Adapter<ClothsTypeAdapter.ClothViewHolder> {


    List<Cloths_order_pojo> list;
    Context context;

    public ClothsTypeAdapter(List<Cloths_order_pojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ClothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
        View view = inflater.inflate (R.layout.cloths_type_item,parent,false);

        return new ClothViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothViewHolder holder, int position) {

        holder.name.setText (list.get (position).cloths_name);
        holder.price.setText (list.get (position).cloths_price);

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ClothViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        ImageView add;
        ImageView sub;

        public ClothViewHolder(@NonNull View itemView) {
            super (itemView);

            name = itemView.findViewById (R.id.cloth_name);
            price = itemView.findViewById (R.id.cloth_price);
            add = itemView.findViewById (R.id.add);
           sub = itemView.findViewById (R.id.sub);


           itemView.setOnClickListener (new View.OnClickListener () {
               @Override
               public void onClick(View v) {
                   Toast.makeText (context, ""+price.getText ().toString (), Toast.LENGTH_SHORT).show ();
               }
           });


        }
    }

}
