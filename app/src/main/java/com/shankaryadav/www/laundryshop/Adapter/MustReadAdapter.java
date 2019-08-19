package com.shankaryadav.www.laundryshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shankaryadav.www.laundryshop.MustReadContentLoader;
import com.shankaryadav.www.laundryshop.MustReadPojo;
import com.shankaryadav.www.laundryshop.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MustReadAdapter extends RecyclerView.Adapter<MustReadAdapter.MustReadViewHolder> {

List<MustReadPojo> list;
Context context;

    public MustReadAdapter(List<MustReadPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MustReadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());

        View view = inflater.inflate (R.layout.must_read_item,parent,false);

        return new MustReadViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MustReadViewHolder holder, int position) {

        Glide.with (context).load (list.get (position).getArticles ().get (position).getUrlToImage ()).into (holder.himgv);
        Glide.with (context).load (list.get (position).getArticles ().get (position).getUrlToImage ()).into (holder.htimgv);

        holder.title.setText (list.get (position).getArticles ().get (position).getTitle ());

       // holder.date.setText (dateFormatter (list.get (position).getArticles ().get (position).getPublishedAt ()));


    }

    @Override
    public int getItemCount() {
        return list.size ();
    }


    public class MustReadViewHolder extends RecyclerView.ViewHolder{

        ImageView himgv;
        ImageView htimgv;
        TextView title;
        TextView date;
        LinearLayout readmore;

        public MustReadViewHolder(@NonNull View itemView) {
            super (itemView);

            himgv = itemView.findViewById (R.id.mr_half_imageview);
            htimgv = itemView.findViewById (R.id.mr_halftrans_imageview);
            title = itemView.findViewById (R.id.mr_textview);
            date = itemView.findViewById (R.id.mr_date);
            readmore = itemView.findViewById (R.id.mr_readmore);


            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition ();

                    if (pos>=0 || (v.getId () == R.id.mr_readmore)){
                        Intent intent = new Intent (context, MustReadContentLoader.class);
                        intent.putExtra ("url",list.get (pos).getArticles ().get (pos).getUrl ());
                        context.startActivity (intent);
                    }

                }
            });


        }
    }

    public String dateFormatter(String simdate){
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date ();
        try{
            date = dateFormat.parse(simdate);//You will get date object relative to server/client timezone wherever it is parsed

        } catch (ParseException e) {
            e.printStackTrace ();
        }
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
}
