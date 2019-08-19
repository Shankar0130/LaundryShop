package com.shankaryadav.www.laundryshop;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shankaryadav.www.laundryshop.Adapter.MustReadAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MustReadsFragment extends Fragment {
RecyclerView recyclerView;
Activity activity;

List<MustReadPojo> list = new ArrayList<> ();
List<Article> articles = new ArrayList<> ();

RequestQueue requestQueue;

    private ShimmerFrameLayout mShimmerViewContainer;

String API = "https://newsapi.org/v2/everything?q=fashion&from=2019-07-20&to=2019-07-29&sortBy=popularity&apiKey=99b8f58c2543478ca88d880b2e0ba900";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate (R.layout.fragment_must_reads, container, false);

        Toast.makeText (this.getActivity (), ""+dateFormat (), Toast.LENGTH_SHORT).show ();

        activity = getActivity ();
        recyclerView = view.findViewById (R.id.mustread_recy);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        recyclerView.setLayoutManager (new LinearLayoutManager (activity));
        recyclerView.addItemDecoration(new DividerItemDecoration (activity,
                DividerItemDecoration.VERTICAL));

        loadurl ();


        return view;
    }


    public void loadurl(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, API,
                null, new Response.Listener<JSONObject> () {
            @Override
            public void onResponse(JSONObject response) {
                getValue (response);
                mShimmerViewContainer.stopShimmer ();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

        },
                new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        requestQueue = Volley.newRequestQueue (getActivity ());

        requestQueue.add (jsonObjectRequest);

    }

    private void getValue(JSONObject response) {
        try{
            JSONArray jsonArray = response.getJSONArray ("articles");

            for (int i =0; i<jsonArray.length ();i++){
                String title = jsonArray.getJSONObject (i).getString ("title");
                String description = jsonArray.getJSONObject (i).getString ("description");
                String url = jsonArray.getJSONObject (i).getString ("url");
                String urltoimage = jsonArray.getJSONObject (i).getString ("urlToImage");
                String date = jsonArray.getJSONObject (i).getString ("publishedAt");

                MustReadPojo mustReadPojo = new MustReadPojo ();
                Article article = new Article ();

                article.setTitle (title);
                article.setDescription (description);
                article.setUrl (url);
                article.setUrlToImage (urltoimage);
                article.setPublishedAt (date);

                articles.add (article);

                mustReadPojo.setArticles (articles);

                list.add (mustReadPojo);

            }



        }catch (JSONException e) {
            e.printStackTrace ();
        }

        recyclerView.setAdapter (new MustReadAdapter (list,activity));


    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer ();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer ();
        super.onPause();
    }


    public String dateFormat(){
        Date mydate = new Date();

        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dmy = dmyFormat.format(mydate);

        return dmy;
    }


}
