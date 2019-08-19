package com.shankaryadav.www.laundryshop;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private static FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            String utilname = "";

            switch (item.getItemId ()) {
                case R.id.navigation_home:

                    fragment =  new HomeFragment ();

                    utilname = Utils.Home_Fragment;

                    getSupportActionBar ().setTitle (R.string.title_home);
                    
                    break;
                case R.id.navigation_order:

                    fragment = new OrderFragment ();

                    utilname = Utils.Order_Fragment;

                    getSupportActionBar ().setTitle (R.string.orders);


                    break;
                case R.id.navigation_category:

                    fragment = new CategoriesFragment ();

                    utilname = Utils.Category_Fragment;

                    getSupportActionBar ().setTitle (R.string.category);


                    break;
                case R.id.navigation_account:

                    fragment = new AccountFragment ();

                    utilname = Utils.Account_Fragment;

                    getSupportActionBar ().setTitle (R.string.account);

                    break;
                case R.id.navigation_mustreads:

                    fragment = new MustReadsFragment ();

                    utilname = Utils.MustReads_Fragment;

                    getSupportActionBar ().setTitle (R.string.must_reads);



                    break;
            }
            return loadFragment (fragment, utilname);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        BottomNavigationView navView = findViewById (R.id.nav_view);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);

        setSupportActionBar (toolbar);


        navView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);









        int key = getIntent ().getIntExtra ("key",0);

        if (key == 0){
            View homeview = navView.findViewById(R.id.navigation_home);
            homeview.performClick();
        } else {
            View view = navView.findViewById(R.id.navigation_order);
            view.performClick();
        }

    }





    public boolean loadFragment(Fragment fragment,String utilname){

        if (fragment != null){

            FragmentTransaction fragmentTransaction  = getSupportFragmentManager ().beginTransaction ();

            fragmentTransaction.replace (R.id.framelayout,fragment,utilname).commit ();

            return  true;

        }

        return false;

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.purse) {
            startActivity (new Intent (MainActivity.this,MyWallet.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment, String utilname){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_exit)
                .replace(R.id.framelayout, fragment,
                        utilname).commit();
    }

//
//    @Override
//    public void onBackPressed() {
////        Fragment HomeFragment = fragmentManager
////                .findFragmentByTag(Utils.Home_Fragment);
////
////        Fragment OrderFragment = fragmentManager
////                .findFragmentByTag(Utils.Order_Fragment);
////
////        Fragment CategoriesFragment = fragmentManager
////                .findFragmentByTag(Utils.Category_Fragment);
////
////        Fragment AccountFragment = fragmentManager
////                .findFragmentByTag(Utils.Account_Fragment);
////
////        Fragment MustReadsFragment = fragmentManager
////                .findFragmentByTag(Utils.MustReads_Fragment);
//
//        finish ();
//
//        super.onBackPressed ();
//    }
}
