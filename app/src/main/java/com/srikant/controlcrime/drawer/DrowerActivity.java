package com.srikant.controlcrime.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.srikant.controlcrime.Activity.MainActivity;
import com.srikant.controlcrime.Activity.MapsActivity;
import com.srikant.controlcrime.R;

public class DrowerActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    FrameLayout activityContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void setContentView(int layoutResID)
    {
        final DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.drower, null);
        activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView menuLeft = (ImageView) findViewById(R.id.menuLeft);
        menuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullView.isDrawerOpen(GravityCompat.START)) {
                    fullView.closeDrawer(GravityCompat.START);
                } else {
                    fullView.openDrawer(GravityCompat.START);
                }
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            try {
                switch(item.getItemId()) {
                    case R.id.nav_home:
                        Intent home=new Intent(this, MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_map:
                        Intent map=new Intent(this, MapsActivity.class);
                        startActivity(map);
                        break;

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_container);
                drawer.closeDrawer(GravityCompat.START);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return true;
    }
}
