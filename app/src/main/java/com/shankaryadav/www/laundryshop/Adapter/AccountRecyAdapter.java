package com.shankaryadav.www.laundryshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shankaryadav.www.laundryshop.AccountRecyPojo;
import com.shankaryadav.www.laundryshop.MainActivity;
import com.shankaryadav.www.laundryshop.ManageAddress;
import com.shankaryadav.www.laundryshop.Notification;
import com.shankaryadav.www.laundryshop.R;

import java.util.List;

public class AccountRecyAdapter extends RecyclerView.Adapter<AccountRecyAdapter.AccountRecyViewHolder> {

    List<AccountRecyPojo> list;
    Context context;

    private int key = 0;


    public AccountRecyAdapter(List<AccountRecyPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountRecyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
        View view = inflater.inflate (R.layout.account_recy_item,parent,false);
        return  new AccountRecyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountRecyViewHolder holder, int position) {

        Glide.with (context).load (list.get (position).icon).into (holder.imageView);
        holder.textView.setText (list.get (position).accncate);

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class AccountRecyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public AccountRecyViewHolder(@NonNull View itemView) {
            super (itemView);

            imageView = itemView.findViewById (R.id.acc_icon);
            textView = itemView.findViewById (R.id.acc_text);


            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition ();

                    switch (position){
                        case 0:
                            context.startActivity (new Intent (context, Notification.class));
                            break;
                        case 1:

                            Intent intent = new Intent (context, MainActivity.class);

                            key++;

                            intent.putExtra ("key",key);
                            context.startActivity (intent);


                            ((Activity)context).finish ();

                            break;
                        case 2:
                            context.startActivity (new Intent (context, ManageAddress.class));

                            break;
                        case 3:
                            Toast.makeText (context, ""+position, Toast.LENGTH_SHORT).show ();
                            break;
                        case 4:

                            break;
                        case 5:

                            break;
                        default:

                            break;
                    }

                }
            });

        }


    }


}
