package com.example.mahmoud.secretarysystem.secretary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mahmoud.secretarysystem.R;


/**
 */

public class CardViewAdapterSecAppointments extends BaseAdapter{
    private Context context;
    private String[] mDateArray;
    private String[] mDescArray;
    private String[] mClientsArray;

    private TextView mDateView ,mDescView,mClientView;

    public CardViewAdapterSecAppointments(Context context, String[] dare , String[] desc, String[] client ) {
        this.context=context;
        this.mDateArray=dare;
        this.mDescArray=desc;
        this.mClientsArray=client;
    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return mDateArray.length;
    }
    @Override
    public Object getItem(int position) {
        /*return the item at posion -position-*/
        return null;
    }

    @Override
    public long getItemId(int position) {
        /*return the id of the row which in this case the index of the array*/
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.appoint_item,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.appoint_item, null);


            mDateView= (TextView) v.findViewById(R.id.text_Date_Sec_Appoint);
            mDescView= (TextView) v.findViewById(R.id.text_Desc_Sec_Appoint);
            mClientView= (TextView) v.findViewById(R.id.text_Sec_Client_Appoint);

            mDateView.setText(""+mDateArray[position]);
            mDescView.setText(""+mDescArray[position]);
            mClientView.setText(""+mClientsArray[position]);




        }else {
            v = (View) convertView;
        }


        return v;
    }
}
