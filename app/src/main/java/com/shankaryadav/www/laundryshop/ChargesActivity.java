package com.shankaryadav.www.laundryshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.shankaryadav.www.laundryshop.Adapter.ChargesCateAdapter;

public class ChargesActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    ChargesCateAdapter chargesCateAdapter;

    androidx.appcompat.widget.Toolbar toolbar;

    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_charges);



        relativeLayout = findViewById (R.id.charge_rellay);

       // relativeLayout.setVisibility (View.INVISIBLE);

        toolbar = findViewById (R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = findViewById (R.id.charges_tabLayout);
        viewPager = findViewById (R.id.charges_viewPager);


        chargesCateAdapter = new ChargesCateAdapter (getSupportFragmentManager ());


        chargesCateAdapter.addFragment (new MenFragment (),"men");
        chargesCateAdapter.addFragment (new WomenFragment (),"women");
        chargesCateAdapter.addFragment (new KidsFragment (),"kids");
        chargesCateAdapter.addFragment (new Household(),"Household");
        chargesCateAdapter.addFragment (new OtherFragment (),"Others");


        viewPager.setAdapter (chargesCateAdapter);


        tabLayout.setupWithViewPager (viewPager);

    }
}
