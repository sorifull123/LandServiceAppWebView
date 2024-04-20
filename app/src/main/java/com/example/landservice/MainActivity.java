package com.example.landservice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    LinearLayout firstitem,secondtitem,thriddtitem,foredtitem,fiveitem;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar materialToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        materialToolbar = findViewById(R.id.materialToolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        firstitem = findViewById(R.id.firstitem);
        secondtitem = findViewById(R.id.secondtitem);
        thriddtitem = findViewById(R.id.thriddtitem);
        foredtitem = findViewById(R.id.foredtitem);
        fiveitem = findViewById(R.id.fiveitem);


        //--------navigation drawer code here -------

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,materialToolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {




               Toast.makeText(MainActivity.this, "Please Wait....", Toast.LENGTH_SHORT).show();
               String url = "https://sorifulhasan.000webhostapp.com/Land%20Service%20app%20json/land.json";




           JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {

                       try {
                           JSONObject JSONObject = response.getJSONObject(0);
                           String privecy = JSONObject.getString("privecyLink");
                           String rating = JSONObject.getString("rating");
                           String moreApp = JSONObject.getString("moreApp");
                           String shar_app = JSONObject.getString("shar_app");

                           if (item.getItemId()==R.id.PrivacyPolicy){
                               gotoUrl(""+privecy);
                           } else if (item.getItemId()==R.id.rating) {
                               gotoUrl(""+rating);
                           } else if (item.getItemId()==R.id.moreApp) {
                               gotoUrl(""+moreApp);
                           } else if (item.getItemId()==R.id.shareApp){
                               gotoUrl(""+shar_app);
                           }

                       } catch (JSONException e) {
                           throw new RuntimeException(e);
                       }

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });

               RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
               queue.add(arrayRequest);









               return true;
           }
       });





        // internet ck

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo!=null){


        }
        else {

          new AlertDialog.Builder(MainActivity.this)
                    .setTitle("আপনার ইন্টারনেট সংযোগ বিচ্ছিন্ন রয়েছে!")
                    .setMessage("এই এপ টি ব্যবহার করতে হলে অবশ্যই আপনাকে  ইন্টারনেট সংযোগ চালু রাখতে হবে !!")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                  .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                      }
                  })

                  .create()
                  .show();
        }



        firstitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                WebViewActivity. webpage ="https://www.eporcha.gov.bd/";

                startActivity(new Intent(MainActivity.this,WebViewActivity.class));


            }
        });

        secondtitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                WebViewActivity. webpage ="https://land.gov.bd/%E0%A6%AD%E0%A7%82%E0%A6%AE%E0%A6%BF-%E0%A6%B8%E0%A7%87%E0%A6%AC%E0%A6%BE-%E0%A6%AB%E0%A6%B0%E0%A7%8D%E0%A6%AE/";

                startActivity(new Intent(MainActivity.this,WebViewActivity.class));


            }
        });

        thriddtitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                WebViewActivity. webpage ="https://www.ldtax.gov.bd/";

                startActivity(new Intent(MainActivity.this,WebViewActivity.class));


            }
        });



        foredtitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewActivity. webpage ="https://emtraining.land.gov.bd/application";

                startActivity(new Intent(MainActivity.this,WebViewActivity.class));


            }
        });


        fiveitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewActivity. webpage =" https://inheritance.gov.bd/";

                startActivity(new Intent(MainActivity.this,WebViewActivity.class));


            }
        });







    } // On Create end here

  private void gotoUrl (String s){

        try {
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW,uri));

        }catch (Exception e){

            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();

        }

    }




}