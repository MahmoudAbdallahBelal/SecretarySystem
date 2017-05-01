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
    public static String GET_MANAGERS = "get_managers";
    public static String UPDATE_SECRETARY = "update_secretary";
    public static String GET_SECRETARY= "get_secretary";
    public static String GET_CLIENR= "get_clients";
    public static String ADD_APPOINTS= "add_appoint";
    public static String GET1_APPOINTS= "get_appoints";

    public static String ADD_TASK= "add_task";
    public static String GET1_TASKS= "get_tasks";
    public static String UPDATE_TASK= "update_task";
    public static String DELETE_TASK= "delete_task";

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


    //------ 2- get_managers ---------------------------------------------------------------------------------------------//
    public void get_managers(final Activity activity,final request_interface request_interface)
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
                params.put(TAG, GET_MANAGERS);
                return params;
            }

        };
        queue.add(request);
    }


//---------------------------------------------------------------------------------------------//
public void update_secretary(final Activity activity,final  int id ,final String password , final  String manager_name,final request_interface request_interface)
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

            params.put(ID, ""+id);
            params.put(PASSWORD, password);
            params.put(MANAGER_NAME, manager_name);
            params.put(TAG, UPDATE_SECRETARY);
            return params;
        }

    };
    queue.add(request);
}
    public void get_secretary(final Activity activity,final request_interface request_interface)
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
                params.put(TAG, GET_SECRETARY);
                return params;
            }

        };
        queue.add(request);
    }

//==================================================================================================//
public void get_clients(final Activity activity,final request_interface request_interface)
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
            params.put(TAG, GET_CLIENR);
            return params;
        }

    };
    queue.add(request);
}
//====================================================================================================//
public void addAppointments(final Activity activity, final String date, final String time , final String priority , final String description , final String client , final String manager , final String secretary, final request_interface request_interface)
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
            params.put(DATE, date);
            params.put(TIME, time);
            params.put(PRIORITY, priority);
            params.put(DESCRIPTION, description);
            params.put(CLIENT, client);
            params.put(MANAGER, manager);
            params.put(SECRETARY, secretary);

            params.put(TAG, ADD_APPOINTS);
            return params;
        }

    };
    queue.add(request);
}
    //------  ---------------------------------------------------------------------------------------------//
    public void getAppoints(final Activity activity,final request_interface request_interface)
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
                params.put(TAG, GET1_APPOINTS);
                return params;
            }

        };
        queue.add(request);
    }
//=============================================================================================================//

    //====================================================================================================//
    public void addTask(final Activity activity, final String date, final String description , final String notes , final String manager , final String secretary, final request_interface request_interface)
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
                params.put(DATE, date);
                params.put(DESCRIPTION, description);
                params.put(NOTES, notes);
                params.put(MANAGER, manager);
                params.put(SECRETARY, secretary);

                params.put(TAG, ADD_TASK);
                return params;
            }

        };
        queue.add(request);
    }


    //=====================================================================================//
    public void getTasks(final Activity activity,final request_interface request_interface)
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
                params.put(TAG, GET1_TASKS);
                return params;
            }

        };
        queue.add(request);
    }

//===================================================================================//

    public void updateTask(final Activity activity,final  int id ,final String date , final  String description,final String notes ,final  String manager ,final String secretary,final request_interface request_interface)
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

                params.put(ID, ""+id);
                params.put(DATE, date);
                params.put(DESCRIPTION, description);
                params.put(NOTES, notes);
                params.put(MANAGER, manager);
                params.put(SECRETARY, secretary);

                params.put(TAG, UPDATE_TASK);
                return params;
            }

        };
        queue.add(request);
    }







//====================================================================//

    public void deleteTask(final Activity activity,final  int id,final request_interface request_interface)
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

                params.put(ID, ""+id);
                params.put(TAG, DELETE_TASK);
                return params;
            }

        };
        queue.add(request);
    }

}