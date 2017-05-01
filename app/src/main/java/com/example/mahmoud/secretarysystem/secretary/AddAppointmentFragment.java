package com.example.mahmoud.secretarysystem.secretary;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.login.LoginFragment;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAppointmentFragment extends Fragment {

    private EditText mDate,mTime,mDesc;
    private  Spinner mSpinnerManagers , mSpinnerClients, mSpinnerSecretary , getmSpinnerPriority;
    private Button mSaveAppointment;
    private WebServices mWebServices;
    private  View mView;
    private LoginFragment mLoginFragment;

    private ArrayAdapter mManagerAdapter, mClientAdapter, mSecretaryAdapter , mPrioriytAdapter;
    private  StringBuffer mManagerBuffer , mClientBuffer , mSecretaryBuffer;
    private  String mMangerArray[] , mClientArray[] , mSecretaryArray[];
   private  String priority[]={"high" ,"low"};
    int yr, month, day;
     int hour,min;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.sec_fragment_add_appointment, container, false);
        mSaveAppointment= (Button) mView.findViewById(R.id.Button_Sec_AddAppointment);
        mClientBuffer=new StringBuffer();
        mManagerBuffer=new StringBuffer();
        mSecretaryBuffer=new StringBuffer();

        mClientArray=new String[]{"client"};
        mDate= (EditText) mView.findViewById(R.id.Edit_Sec_Date);
        mTime= (EditText) mView.findViewById(R.id.Edit_Sec_Time);
        mDesc= (EditText) mView.findViewById(R.id.Edit_Sec_Drsc);

        mSpinnerClients= (Spinner) mView.findViewById(R.id.Spinner_Sec_Clients);
        mSpinnerManagers= (Spinner) mView.findViewById(R.id.Spinner_Sec_Managers);
        mSpinnerSecretary= (Spinner) mView.findViewById(R.id.Spinner_Sec_Secretaries);
        getmSpinnerPriority= (Spinner) mView.findViewById(R.id.Spinner_Sec_priority);


        Calendar today = Calendar.getInstance();
        yr = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);


        mLoginFragment=new LoginFragment();
        mWebServices=new WebServices();
        mLoginFragment.sharedPreferences=getActivity().getSharedPreferences("sec",0);



        mClientAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,mClientArray);
        mSpinnerClients.setAdapter(mClientAdapter);


        mPrioriytAdapter=new ArrayAdapter(getActivity() ,android.R.layout.simple_list_item_1,priority);
        getmSpinnerPriority.setAdapter(mPrioriytAdapter);

/*
    mWebServices.get_clients(getActivity(), new request_interface() {
        @Override
        public void onResponse(String response) {
            JSONObject jsonResponse = null;
            try {

                jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("clients");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject search_object = jsonArray.getJSONObject(i);
                    mClientBuffer.append(search_object.getString("first_name") + " "+search_object.getString("last_name") + ":");




                }

                mClientArray = mClientBuffer.toString().split(":");
            }

            catch (JSONException e) {
                Toast.makeText(getActivity(), "error Server.", Toast.LENGTH_SHORT).show();
            }


            mClientAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,mClientArray);
            mSpinnerClients.setAdapter(mClientAdapter);
        }

        @Override
        public void onError() {

        }
    });

*/


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
                mSpinnerSecretary.setAdapter(mSecretaryAdapter);
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
                mSpinnerManagers.setAdapter(mManagerAdapter);
            }

            @Override
            public void onError() {

            }
        });






        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), mDateSetListener, yr, month, day).show();

            }
        });
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
new TimePickerDialog(getActivity(),mTimeSetListener ,hour,min,false).show();

            }
        });




        mSaveAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebServices.addAppointments(getActivity(), mDate.getText().toString(), mTime.getText().toString(), getmSpinnerPriority.getSelectedItem().toString(), mDesc.getText().toString(), mSpinnerClients.getSelectedItem().toString(), mSpinnerManagers.getSelectedItem().toString(), mSpinnerSecretary.getSelectedItem().toString(), new request_interface() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "added successfully.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getContext(), "error Server.", Toast.LENGTH_SHORT).show();

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
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(
                TimePicker view, int hourOfDay, int minuteOfHour)
        {
            hour = hourOfDay;
            min = minuteOfHour;
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
            Date date = new Date(0,0,0, hour, min);
            String strDate = timeFormat.format(date);
            // Toast.makeText(getActivity(), "You have selected " + strDate, Toast.LENGTH_SHORT).show();
            mTime.setText(""+strDate);

        }
    };
}
