package com.example.mahmoud.secretarysystem.manager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.login.LoginFragment;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerEditInformationFragment extends Fragment {


    private EditText mFirstName,mLastName,mUserName,mEmail,mPassword;
    private Button mEdit;
    private View mView;
    private WebServices mWebServices;
    private LoginFragment loginFragment;


    private  String mDefaultArray[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.mng_fragment_manager_edit_information, container, false);


        mWebServices=new WebServices();
        loginFragment=new LoginFragment();
        loginFragment.sharedPreferences=getActivity().getSharedPreferences("sec",0);


        mFirstName = (EditText) mView .findViewById(R.id.Edit_mng_FirstName);
        mLastName= (EditText) mView.findViewById(R.id.Edit_mng_LastName);
        mUserName= (EditText) mView.findViewById(R.id.Edit_mng_UserName);
        mEmail= (EditText) mView.findViewById(R.id.Edit_mng_Email);
        mPassword= (EditText) mView.findViewById(R.id.Edit_mng_Password);
        mEdit= (Button) mView.findViewById(R.id.Button_Edit_mng_Info);


        mFirstName .setText(loginFragment.sharedPreferences.getString("first_name",""));
        mLastName .setText(loginFragment.sharedPreferences.getString("last_name",""));
        mUserName .setText(loginFragment.sharedPreferences.getString("user_name",""));
        mEmail .setText(loginFragment.sharedPreferences.getString("email",""));
        mPassword .setText(loginFragment.sharedPreferences.getString("password",""));



        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebServices.setUpdateManager(getActivity(), loginFragment.sharedPreferences.getInt("id", 2017), mPassword.getText().toString(), new request_interface() {
                    @Override
                    public void onResponse(String response) {

                       // refresh page
                        ManagerEditInformationFragment EditInformationFragment=new ManagerEditInformationFragment();
                        FragmentManager fm =getActivity().getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.content_mng__home,EditInformationFragment);
                        ft.commit();
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });



        return  mView;

    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, 500);
    }


}
