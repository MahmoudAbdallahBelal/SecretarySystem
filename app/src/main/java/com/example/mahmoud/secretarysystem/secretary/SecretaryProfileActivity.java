package com.example.mahmoud.secretarysystem.secretary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.login.LoginFragment;

public class SecretaryProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  TextView mFullName,mUserType;
    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity_secretary_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loginFragment=new LoginFragment();
        loginFragment.sharedPreferences=getSharedPreferences("sec",0);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
         mFullName= (TextView) headerView.findViewById(R.id.textView_FullName_Profile);
         mUserType= (TextView) headerView.findViewById(R.id.textView_UserType);

        String fullName=loginFragment.sharedPreferences.getString("first_name","*")+" "+loginFragment.sharedPreferences.getString("last_name","*");
        String userType=loginFragment.sharedPreferences.getString("user_type","*");

        mFullName.setText(fullName);
        mUserType.setText(userType);


        SecretaryEditInformationFragment secretaryEditInformationFragment=new SecretaryEditInformationFragment();
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.content_secretary_profile,secretaryEditInformationFragment);
        ft.commit();





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.secretary_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            SecretaryEditInformationFragment secretaryEditInformationFragment=new SecretaryEditInformationFragment();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_secretary_profile,secretaryEditInformationFragment);
            ft.commit();

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            AddAppointmentFragment addAppointmentFragment=new AddAppointmentFragment();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_secretary_profile,addAppointmentFragment);
            ft.commit();


        } else if (id == R.id.nav_slideshow) {

            ShowAppointmentsFragment showAppointmentsFragment=new ShowAppointmentsFragment();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_secretary_profile,showAppointmentsFragment);
            ft.commit();

        } else if (id == R.id.nav_manage) {
            AddTaskFragment addTaskFragment=new AddTaskFragment();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_secretary_profile,addTaskFragment);
            ft.commit();

        } else if (id == R.id.nav_share) {
            ShowTasksFragment showTasksFragment=new ShowTasksFragment();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_secretary_profile,showTasksFragment);
            ft.commit();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
