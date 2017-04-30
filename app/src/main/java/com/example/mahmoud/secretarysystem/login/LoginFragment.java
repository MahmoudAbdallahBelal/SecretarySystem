package com.example.mahmoud.secretarysystem.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.secretary.SecretaryProfileActivity;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import org.json.JSONException;
import org.json.JSONObject;



public class LoginFragment extends Fragment {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    private AutoCompleteTextView mPasswordView;
    private AutoCompleteTextView mUserName;

    private WebServices web;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.login_fragment_login, parent, false);

        MainActivity.sFlag=1;

        sharedPreferences=getActivity().getSharedPreferences("sec",0);
        editor = sharedPreferences.edit();


        web= new WebServices();





        // Set up the login form.
        mPasswordView = (AutoCompleteTextView) v.findViewById(R.id.password);
        mUserName = (AutoCompleteTextView) v.findViewById(R.id.email);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                return false;
            }
        });


        Button mEmailSignInButton = (Button) v.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptLogin();
            }
        });


        return v;
    }






    //TODO Login Action
    private void attemptLogin()
    {
    web.login_user(getActivity(), mUserName.getText().toString().trim(), mPasswordView.getText().toString().trim(), new request_interface() {
        @Override
        public void onResponse(String response) {
            try {

                JSONObject jsonObject = new JSONObject(response);
                String login_response = jsonObject.getString("login_response");

                if (login_response.equals("done")) {

                    int id = jsonObject.getInt("id");
                    String first_name = jsonObject.getString("first_name");
                    String last_name = jsonObject.getString("last_name");
                    String email = jsonObject.getString("email");
                    String user_type = jsonObject.getString("user_type");
                    String user_name = jsonObject.getString("user_name");
                    String password = jsonObject.getString("password");
                    String manager_name = jsonObject.getString("manager_name");
                    //-----------------------------------------------------//
                    editor.putInt("id",  id);
                    editor.putString("first_name",first_name);
                    editor.putString("last_name",last_name);
                    editor.putString("email",email);
                    editor.putString("user_type",user_type);
                    editor.putString("user_name",user_name);
                    editor.putString("password",password);
                    editor.putString("manager_name", manager_name);


                    editor.commit();
                    if (user_type.equals("secretary")) {

                        startActivity(new Intent(getActivity(), SecretaryProfileActivity.class));

                    }
                    else if (user_type.equals("manager")) {
                    }





                } else if (login_response.equals("incorrect password")) {

                    mPasswordView.setError("incorrect password");
                }

                else {

                     mUserName.setError("incorrect username");
                }

            } catch (JSONException e) {
                Toast.makeText(getActivity(), "try again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError() {
            Toast.makeText(getActivity(), "problem to Connect Server.", Toast.LENGTH_SHORT).show();

        }
    });
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, 500);
    }
}
