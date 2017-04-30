package com.example.mahmoud.secretarysystem.secretary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
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
public class SecretaryEditInformationFragment extends Fragment {


    private EditText mFirstName,mLastName,mUserName,mEmail,mPassword;
    private Spinner mManagerName;
    private Button mEdit;
    private View mView;
    private ArrayAdapter mAdapter ;
    private  StringBuffer  mFirstNameManagerBuffer;
    private String  mFirstNameManagers[] ;
    private WebServices mWebServices;
    private LoginFragment loginFragment;


    private  String mDefaultArray[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.sec_fragment_secretary_edit_information, container, false);

        mFirstNameManagerBuffer =new StringBuffer();
        mWebServices=new WebServices();
        loginFragment=new LoginFragment();
        loginFragment.sharedPreferences=getActivity().getSharedPreferences("sec",0);


        mFirstName = (EditText) mView .findViewById(R.id.Edit_Sec_FirstName);
        mLastName= (EditText) mView.findViewById(R.id.Edit_Sec_LastName);
        mUserName= (EditText) mView.findViewById(R.id.Edit_Sec_UserName);
        mEmail= (EditText) mView.findViewById(R.id.Edit_Sec_Email);
        mPassword= (EditText) mView.findViewById(R.id.Edit_Sec_Password);
        mEdit= (Button) mView.findViewById(R.id.Button_Edit_Sec_Info);

        mManagerName= (Spinner) mView.findViewById(R.id.Spinner_Sec_ManagerName);


        mFirstName.setEnabled(false);
        mLastName.setEnabled(false);
        mUserName.setEnabled(false);
        mEmail.setEnabled(false);


        mFirstName .setText(loginFragment.sharedPreferences.getString("first_name",""));
        mLastName .setText(loginFragment.sharedPreferences.getString("last_name",""));
        mUserName .setText(loginFragment.sharedPreferences.getString("user_name",""));
        mEmail .setText(loginFragment.sharedPreferences.getString("email",""));
        mPassword .setText(loginFragment.sharedPreferences.getString("password",""));


        mDefaultArray=new String[]{loginFragment.sharedPreferences.getString("manager_name","")};
        mAdapter=new ArrayAdapter(getActivity() , android.R.layout.simple_list_item_1,mDefaultArray);
        mManagerName.setAdapter(mAdapter);



        mWebServices.get_managers(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                try {

                    jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("managers");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mFirstNameManagerBuffer.append(search_object.getString("first_name") + " "+search_object.getString("last_name") + ":");
                    }

                    mFirstNameManagers = mFirstNameManagerBuffer.toString().split(":");
                }
                catch (JSONException e) {
                    Toast.makeText(getActivity(), "error Server.", Toast.LENGTH_SHORT).show();
                }

                mAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,mFirstNameManagers);
                mManagerName.setAdapter(mAdapter);

            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), "error Server.", Toast.LENGTH_SHORT).show();
            }
        });





        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebServices.update_secretary(getActivity(), loginFragment.sharedPreferences.getInt("id", 2017), mPassword.getText().toString(), mManagerName.getSelectedItem().toString(), new request_interface() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getActivity(), ""+mManagerName.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                        Toast.makeText(getActivity(), "updated successfully.", Toast.LENGTH_SHORT).show();
                        // refresh page
                        SecretaryEditInformationFragment secretaryEditInformationFragment=new SecretaryEditInformationFragment();
                        FragmentManager fm =getActivity().getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.content_secretary_profile,secretaryEditInformationFragment);
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
