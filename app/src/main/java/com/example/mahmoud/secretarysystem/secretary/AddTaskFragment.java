package com.example.mahmoud.secretarysystem.secretary;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.login.LoginFragment;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    private  View mView;
    private Button mAddTask;
    private EditText mDate, mDesc,mNotes;
    private Spinner mManagerSpinner, mSecretarySpinner;
    int yr, month, day;
   private  LoginFragment mLoginFragment;
    private  WebServices mWebServices;

    private ArrayAdapter mManagerAdapter, mSecretaryAdapter ;
    private  StringBuffer mManagerBuffer  , mSecretaryBuffer;
    private  String mMangerArray[] , mSecretaryArray[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.sec_fragment_add_task, container, false);
        mAddTask= (Button) mView .findViewById(R.id.Button_Sec_AddTask);
        mDate= (EditText) mView.findViewById(R.id.Edit_Sec_Date_Task);
        mDesc= (EditText) mView.findViewById(R.id.Edit_Sec_Desc_Task);
        mNotes= (EditText) mView.findViewById(R.id.Edit_Sec_Notes_Task);

        mManagerSpinner= (Spinner) mView.findViewById(R.id.Spinner_Sec_Managers_Task);
        mSecretarySpinner= (Spinner) mView.findViewById(R.id.Spinner_Sec_Secretaries_Task);


        mManagerBuffer=new StringBuffer();
        mSecretaryBuffer=new StringBuffer();
        mLoginFragment=new LoginFragment();
        mWebServices=new WebServices();
        mLoginFragment.sharedPreferences=getActivity().getSharedPreferences("sec",0);



        Calendar today = Calendar.getInstance();
        yr = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);


        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), mDateSetListener, yr, month, day).show();

            }
        });









        mWebServices.get_secretary(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonResponse = null;
                try {

                    jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("secretary");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mSecretaryBuffer.append(search_object.getString("first_name") + " "+search_object.getString("last_name") + ":");




                    }

                    mSecretaryArray = mSecretaryBuffer.toString().split(":");
                }

                catch (JSONException e) {
                    Toast.makeText(getActivity(), "error Server.", Toast.LENGTH_SHORT).show();
                }


                mSecretaryAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,mSecretaryArray);
                mSecretarySpinner.setAdapter(mSecretaryAdapter);
            }

            @Override
            public void onError() {

            }
        });


        mWebServices.get_managers(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {


                JSONObject jsonResponse = null;
                try {

                    jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("managers");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mManagerBuffer.append(search_object.getString("first_name") + " "+search_object.getString("last_name") + ":");




                    }

                    mMangerArray = mManagerBuffer.toString().split(":");
                }

                catch (JSONException e) {
                    Toast.makeText(getActivity(), "error Server.", Toast.LENGTH_SHORT).show();
                }


                mManagerAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,mMangerArray);
                mManagerSpinner.setAdapter(mManagerAdapter);
            }

            @Override
            public void onError() {

            }
        });

   mAddTask.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           mWebServices.addTask(getActivity(), mDate.getText().toString(), mDesc.getText().toString(), mNotes.getText().toString(), mManagerSpinner.getSelectedItem().toString(), mSecretarySpinner.getSelectedItem().toString(), new request_interface() {
               @Override
               public void onResponse(String response) {
                   Toast.makeText(getActivity(), "added task.", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onError() {
                   Toast.makeText(getActivity(), "error server.", Toast.LENGTH_SHORT).show();

               }
           });
       }
   });



















        return  mView;

    }















    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(
                DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            yr = year;
            month = monthOfYear;
            day = dayOfMonth;


            mDate.setText(""+(month + 1) + "/" + day + "/" + yr);
        }
    };
}
