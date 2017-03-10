package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.FragmentInflater;

public class AdminMain extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawer;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
   static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
drawer= (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        fragment= new AdminHome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentInflater fInflater=new FragmentInflater(AdminMain.this,fragment,fragmentManager);
        fInflater.loadFragment();
        setupNavigationView();
        //loadFragment();

    }


    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        break;
                    case R.id.nav_progress:
                        navItemIndex = 1;
                        break;
                    case R.id.nav_completed:
                        navItemIndex = 2;
                        break;
                    case R.id.nav_report:
                        navItemIndex = 3;
                        break;
                    default:
                        navItemIndex = 0;
                        break;
                }
                drawer.closeDrawers();
                fragment = getCurrentFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentInflater fInflater=new FragmentInflater(AdminMain.this,fragment,fragmentManager);
                fInflater.loadFragment();
                return true;

            }

        });

    }



    public Fragment getCurrentFragment() {
        switch (navItemIndex) {
            case 0:
                return new AdminHome();
            case 1:
                return new AdminProgress();
            case 2:
                Log.v("......", "completed");
                return new AdminCompleted();
            case 3:
                return new AdminReport();
            default:
                return new AdminHome();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}


