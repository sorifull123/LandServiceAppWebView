package com.example.landservice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.android.material.appbar.MaterialToolbar;

public class WebViewActivity extends AppCompatActivity {


    public static String webpage = "";

    WebView webview;
   public static MaterialToolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = findViewById(R.id.webview);
        toolbar = findViewById(R.id.toolbar);


          networkCHQ();
          IF();






    }// OnCreate method Close Here =======================

    private void networkCHQ(){

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo!=null){

            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(webpage);

        }
        else {

            new AlertDialog.Builder(WebViewActivity.this)
                    .setTitle("আপনার ইন্টারনেট সংযোগ বিচ্ছিন্ন রয়েছে!")
                    .setMessage("দয়া করে আপনার ইন্টারনেট সংযোগ চালু করে আবার চেষ্টা করুন !!")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(R.string.home, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(WebViewActivity.this,MainActivity.class));

                        }
                    })
                    .create()
                    .show();
        }


    }


    public void IF(){

        if (webpage.contains("https://www.eporcha.gov.bd/")){

            toolbar.setTitle("জমি তল্লাশি করুন নিজের নাম দিয়ে");

        } else if (webpage.contains("https://land.gov.bd/%E0%A6%AD%E0%A7%82%E0%A6%AE%E0%A6%BF-%E0%A6%B8%E0%A7%87%E0%A6%AC%E0%A6%BE-%E0%A6%AB%E0%A6%B0%E0%A7%8D%E0%A6%AE/")) {

            toolbar.setTitle("ভূমি সেবা ফরম ডাউনলোড করুন");

        }else if (webpage.contains("https://www.ldtax.gov.bd/")) {

            toolbar.setTitle("ভূমি উন্নয়ন কর সেবা");

        }else if (webpage.contains("https://emtraining.land.gov.bd/application")) {

            toolbar.setTitle("নামজারি ও জমাভাগজমা একত্রিকরণ");

        }else if (webpage.contains("https://inheritance.gov.bd/")) {

            toolbar.setTitle("উত্তরাধিকারী-এক ক্লিকেই সম্পত্তির হিসাব");

        }



    }

}