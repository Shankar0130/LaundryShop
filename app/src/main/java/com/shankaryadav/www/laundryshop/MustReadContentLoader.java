package com.shankaryadav.www.laundryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MustReadContentLoader extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_must_read_content_loader);

//        getSupportActionBar().setBackgroundDrawable(
//                new ColorDrawable (Color.parseColor("#FFFF0000"))
//        );

        webView = findViewById (R.id.webview);
        progressBar = findViewById (R.id.determinateBar);

        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog (MustReadContentLoader.this);

        progressDialog =   ProgressDialog.show(MustReadContentLoader.this, "Webpage is Loading", "Loading...");



        String url = getIntent ().getStringExtra ("url");

        webView.getSettings ().setJavaScriptEnabled (true);
        webView.setWebViewClient (new WebViewController ());
        webView .loadUrl(url);


        webView.setWebChromeClient (new WebChromeClient (){
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    progressBar.setVisibility(View.GONE);
                    progressDialog.dismiss ();
                }
            }
        });


    }


    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted (view, url, favicon);

            progressBar.setVisibility (View.VISIBLE);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished (view, url);

            progressBar.setVisibility (View.INVISIBLE);
            progressDialog.dismiss ();
        }


    }



}
