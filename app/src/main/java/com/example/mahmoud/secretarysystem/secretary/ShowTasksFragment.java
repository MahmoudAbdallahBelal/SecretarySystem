package com.example.mahmoud.secretarysystem.secretary;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mahmoud.secretarysystem.R;
import com.example.mahmoud.secretarysystem.web.WebServices;
import com.example.mahmoud.secretarysystem.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowTasksFragment extends Fragment {

      int counter=0;

   private WebServices mWebServices;
    private  View mView;
    private GridView mGridViewTasks;

    private ArrayAdapter adapter;
    private  CardViewAdapterSecTasks adapterSecTasks;
    private  StringBuffer mDateBuffer   , mDescBuffer ,mNotesBuffer ,mIdBuffer  ,mManagerBuffer,mSecretaryBuffer ;
    private String mDateArray[]   , mDescArray[] ,mIdArray[],mSecretaryArray[],mManagerArray[],mNotesArray[];
    public  int position =0;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.sec_fragment_show_tasks, container, false);
        mWebServices=new WebServices();

           mDateBuffer=new StringBuffer();
           mDescBuffer=new StringBuffer();
           mIdBuffer =new StringBuffer();
           mManagerBuffer=new StringBuffer();
           mSecretaryBuffer=new StringBuffer();
           mNotesBuffer =new StringBuffer();



      mGridViewTasks = (GridView) mView.findViewById(R.id.Grid_Sec_Tasks);




        mWebServices.getTasks(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("tasks");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);

                        mDateBuffer.append(search_object.getString("date") + "#");
                        mIdBuffer.append(search_object.getString("id") + "#");
                        mDescBuffer.append(search_object.getString("description") + "#");
                        mNotesBuffer.append(search_object.getString("notes") + "#");
                        mManagerBuffer.append(search_object.getString("manager") + "#");
                        mSecretaryBuffer.append(search_object.getString("secretary") + "#");

                    }



                    mDateArray=mDateBuffer.toString().split("#");
                    mDescArray=mDescBuffer.toString().split("#");
                    mNotesArray=mNotesBuffer.toString().split("#");
                    mIdArray=mIdBuffer.toString().split("#");
                    mManagerArray=mManagerBuffer.toString().split("#");
                    mSecretaryArray=mSecretaryBuffer.toString().split("#");


                    adapterSecTasks = new CardViewAdapterSecTasks(getActivity(), mDateArray,mDescArray);
                    mGridViewTasks.setAdapter(adapterSecTasks);


                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "error is "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), " server.error", Toast.LENGTH_SHORT).show();
            }
        });




 mGridViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
  position =i;
         final Dialog dialog=new Dialog(getActivity());
         dialog.setContentView(R.layout.task_dialog);
         final EditText date= (EditText) dialog.findViewById(R.id.Edit_Sec_Date_Dialog);
         final EditText notes= (EditText) dialog.findViewById(R.id.Edit_Sec_Notes_Dialog);
         final EditText description= (EditText) dialog.findViewById(R.id.Edit_Sec_description_Dialog);
         final EditText manager= (EditText) dialog.findViewById(R.id.Edit_Sec_manager_Dialog);
         final EditText secretary= (EditText) dialog.findViewById(R.id.Edit_Sec_secretary_Dialog);
         Button editBtn= (Button) dialog.findViewById(R.id.Button_EditTask);
         Button delBtn= (Button) dialog.findViewById(R.id.Button_DeleteTask);



         date.setText(mDateArray[i]);
         notes.setText(mNotesArray[i]);
         description.setText(mDescArray[i]);
         manager.setText(mManagerArray[i]);
         secretary.setText(mSecretaryArray[i]);



         date.setEnabled(false);
         notes.setEnabled(false);
         description.setEnabled(false);
         manager.setEnabled(false);
         secretary.setEnabled(false);



         editBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 counter++;
                 if(counter==1){
                     Toast.makeText(getActivity(), "press again after editing ", Toast.LENGTH_SHORT).show();
                 }
                 date.setEnabled(true);
                 notes.setEnabled(true);
                 description.setEnabled(true);
                 manager.setEnabled(true);
                 secretary.setEnabled(true);

             if (counter ==2 ){

                 android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getActivity());
                   builder.setIcon(R.drawable.logo);
                    builder.setTitle(""+mIdArray[position]);
                     builder.setMessage("confirm Editing.....");
                 builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialogInterface, int i) {

                         mWebServices.updateTask(getActivity(), Integer.parseInt(mIdArray[position]), date.getText().toString(), description.getText().toString(), notes.getText().toString(), manager.getText().toString(), secretary.getText().toString(), new request_interface() {
                             @Override
                             public void onResponse(String response) {
                                 Toast.makeText(getActivity(), "updated successfully.", Toast.LENGTH_SHORT).show();
                          counter=0;
                             dialog.dismiss();
dialogInterface.dismiss();
                                 ShowTasksFragment showTasksFragment=new ShowTasksFragment();
                                 FragmentManager fm =getActivity().getSupportFragmentManager();
                                 FragmentTransaction ft=fm.beginTransaction();
                                 ft.replace(R.id.content_secretary_profile,showTasksFragment);
                                 ft.commit();
                             }


                             @Override
                             public void onError() {
                                 Toast.makeText(getActivity(), "Failed.  error.Server", Toast.LENGTH_LONG).show();
                             }
                         });
                     }
                 });

                 builder.show();
             }


             }
         });

         delBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getActivity());
                 builder.setIcon(R.drawable.logo);
                 builder.setTitle(mIdArray[position]);
                 builder.setMessage("Confirm Delete.");
                 builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialogInterface, int i) {

                         mWebServices.deleteTask(getActivity(), Integer.parseInt(mIdArray[position]), new request_interface() {
                             @Override
                             public void onResponse(String response) {
                                 dialogInterface.dismiss();
                                 dialog.dismiss();
                                 ShowTasksFragment showTasksFragment=new ShowTasksFragment();
                                 FragmentManager fm =getActivity().getSupportFragmentManager();
                                 FragmentTransaction ft=fm.beginTransaction();
                                 ft.replace(R.id.content_secretary_profile,showTasksFragment);
                                 ft.commit();
                             }

                             @Override
                             public void onError() {
                                 Toast.makeText(getActivity(), "Failed .try again error.Server", Toast.LENGTH_LONG).show();
                             }
                         });
                     }
                 });
                 builder.show();





             }
         });


         dialog.show();




     }
 });





        return  mView;
    }

}
