package com.example.mahmoud.secretarysystem.manager;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.secretary.CardViewAdapterSecAppointments;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAppointmentsManagerFragment extends Fragment {


   private WebServices mWebServices;
    private  View mView;
    private GridView mGridView;

    private ArrayAdapter adapter;
    private  CardViewAdapterSecAppointments adapterSecAppointments;
    private  StringBuffer mDateBuffer   , mDescBuffer ,mClintBuffer ,mIdBuffer , mTimeBuffer ,mPriorityBuffer,mManagerBuffer,mSecretaryBuffer ;
    private String mDateArray[]   , mDescArray[] ,mClientArray[] ,mIdArray[],mSecretaryArray[],mManagerArray[],mPriorityArray[],mTimeArray[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.sec_fragment_show_appointments, container, false);
        mWebServices=new WebServices();

           mDateBuffer=new StringBuffer();
           mDescBuffer=new StringBuffer();
           mClintBuffer=new StringBuffer();
           mIdBuffer =new StringBuffer();
           mPriorityBuffer=new StringBuffer();
           mTimeBuffer=new StringBuffer();
           mManagerBuffer=new StringBuffer();
           mSecretaryBuffer=new StringBuffer();



      mGridView = (GridView) mView.findViewById(R.id.Grid_Sec);




        mWebServices.getAppoints(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("appoints");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);

                        mDateBuffer.append(search_object.getString("date") + "#");
                        mIdBuffer.append(search_object.getString("id") + "#");
                        mDescBuffer.append(search_object.getString("description") + "#");
                        mClintBuffer.append(search_object.getString("client") + "#");
                        mTimeBuffer.append(search_object.getString("time") + "#");
                        mPriorityBuffer.append(search_object.getString("priority") + "#");
                        mManagerBuffer.append(search_object.getString("manager") + "#");
                        mSecretaryBuffer.append(search_object.getString("secretary") + "#");

                    }



                    mDateArray=mDateBuffer.toString().split("#");
                    mDescArray=mDescBuffer.toString().split("#");
                    mClientArray=mClintBuffer.toString().split("#");
                    mIdArray=mIdBuffer.toString().split("#");
                    mTimeArray=mTimeBuffer.toString().split("#");
                    mPriorityArray=mPriorityBuffer.toString().split("#");
                    mManagerArray=mManagerBuffer.toString().split("#");
                    mSecretaryArray=mSecretaryBuffer.toString().split("#");


                    adapterSecAppointments = new  CardViewAdapterSecAppointments(getActivity(), mDateArray,mDescArray,mClientArray);
                    mGridView.setAdapter(adapterSecAppointments);


                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), " server.error", Toast.LENGTH_SHORT).show();
            }
        });




 mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

         Dialog dialog=new Dialog(getActivity());
         dialog.setContentView(R.layout.appointment_sec_dialog);
         EditText date= (EditText) dialog.findViewById(R.id.Edit_Sec_Date_Dialog);
         EditText time= (EditText) dialog.findViewById(R.id.Edit_Sec_Time_Dialog);
         EditText priority= (EditText) dialog.findViewById(R.id.Edit_Sec_Priority_Dialog);
         EditText description= (EditText) dialog.findViewById(R.id.Edit_Sec_description_Dialog);
         EditText client= (EditText) dialog.findViewById(R.id.Edit_Sec_client_Dialog);
         EditText manager= (EditText) dialog.findViewById(R.id.Edit_Sec_manager_Dialog);
         EditText secretary= (EditText) dialog.findViewById(R.id.Edit_Sec_secretary_Dialog);

         date.setEnabled(false);
         time.setEnabled(false);
         priority.setEnabled(false);
         description.setEnabled(false);
         client.setEnabled(false);
         manager.setEnabled(false);
         secretary.setEnabled(false);


         date.setText(mDateArray[i]);
         time.setText(mTimeArray[i]);
         priority.setText(mPriorityArray[i]);
         description.setText(mDescArray[i]);
         client.setText(mClientArray[i]);
         manager.setText(mManagerArray[i]);
         client.setText(mClientArray[i]);
         secretary.setText(mSecretaryArray[i]);

         dialog.show();




     }
 });





        return  mView;
    }

}
