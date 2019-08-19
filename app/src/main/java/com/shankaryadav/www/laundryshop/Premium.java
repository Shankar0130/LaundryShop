package com.shankaryadav.www.laundryshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.scwang.wave.MultiWaveHeader;

import java.util.Arrays;

public class Premium extends AppCompatActivity {

    private ViewPager mViewPager;

    private PremiumFragmentPagerAdapter premiumFragmentPagerAdapter;
    private PremiumShadowTransform premiumShadowTransform;

    private boolean mShowingFragments = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_premium);

        mViewPager = findViewById (R.id.viewpager);

        premiumFragmentPagerAdapter = new PremiumFragmentPagerAdapter (getSupportFragmentManager (),dpToPixels (2,this));

        mViewPager.setAdapter(premiumFragmentPagerAdapter);
        mViewPager.setPageTransformer(false, premiumShadowTransform);

//        MultiWaveHeader waveHeader = findViewById(R.id.waveHeader);
//
//
//        waveHeader.setStartColor(R.color.colorPrimary);
//        waveHeader.setCloseColor(R.color.colorPrimaryDark);
//        waveHeader.setColorAlpha(.5f);
//
//        waveHeader.setWaveHeight(50);
//        waveHeader.setGradientAngle(360);
//        waveHeader.setProgress(.8f);
//        waveHeader.setVelocity(1f);
//        waveHeader.setScaleY(-1f);
//
//        waveHeader.setWaves("PairWave");
//
//        waveHeader.start();
//        waveHeader.stop();
//        waveHeader.isRunning();



    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

}
