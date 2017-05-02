package com.example.mahmoud.secretarysystem.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.web.WebServices;


public class MainActivity extends AppCompatActivity {


   static  int sFlag=0;
    int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        LoginFragment loginFragment=new LoginFragment();
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.activity_main,loginFragment);
        ft.commit();


    }

    @Override
    public void onBackPressed() {
        if(sFlag==1){
            counter++;

            if(counter==2){
                finish();
            }

        }

    }

public void logOut(){


}


}
