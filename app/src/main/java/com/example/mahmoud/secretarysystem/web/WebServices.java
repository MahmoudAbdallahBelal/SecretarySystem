package com.example.mahmoud.secretarysystem.web;

/**
 * Created by ahmed on 4/23/2017.
 */

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;


public class WebServices {



    // TODO     "  users  "
    public static String ID = "id";
    public static String FIRST_NAME = "first_name";
    public static String LAST_NAME = "last_name";
    public static String EMAIL = "email";
    public static String USER_TYPE = "user_type";
    public static String USER_NAME = "user_name";
    public static String PASSWORD = "password";
    public static String MANAGER_NAME = "manager_name";


    //  TODO    "  appointments "
    public static String DATE = "date";
    public static String TIME = "time";
    public static String PRIORITY = "priority";
    public static String DESCRIPTION = "description";
    public static String CLIENT = "client";
    public static String MANAGER = "manager";
    public static String SECRETARY = "secretary";


    // TODO    "  tasks "
    public static String NOTES = "notes";




    // TODO Tag that use it to know type of WebService   >> you can here put the TAG  of your method Name ------//
    public static String TAG = "tag";

    public static String USER_LOGIN = "login_user";




    private RequestQueue queue;
    private String url = "http://manager-secretary.000webhostapp.com/uploads/re_tags.php";






    // TODO Login Method ----------------------------------//
    public void login_user(final Activity activity, final String user_name, final String password ,final request_interface request_interface)
    {
        queue = Volley.newRequestQueue(activity);
        final StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
             request_interface.onResponse(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                request_interface.onError();
            }
        }) {

            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USER_NAME, user_name);
                params.put(PASSWORD, password);
                params.put(TAG, USER_LOGIN);
                return params;
            }

        };
        queue.add(request);
    }


    //---------------------------------------------------------------------------------------------------//





}