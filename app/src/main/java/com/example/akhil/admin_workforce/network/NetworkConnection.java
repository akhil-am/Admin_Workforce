package com.example.akhil.admin_workforce.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.akhil.admin_workforce.admin.AdminMain;
import com.example.akhil.admin_workforce.extras.DataClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akhil on 26/01/17.
 */

public class NetworkConnection {

    private Context context;
 public List<DataClass> mList;
   public NetworkConnection(Context context){
        this.context=context;

    }
// Login
public   void login(final String uname, final String pass){
    String mLoginUrl = "http://avipsr.96.lt/login.php";
    Log.v("uname",uname+pass);

    StringRequest stringRequest= new StringRequest(Request.Method.POST, mLoginUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                ProgressDialog progressDialog=new ProgressDialog(context);
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                JSONObject jsonObject= new JSONObject(response);
                Boolean mResponse= jsonObject.getBoolean("code");
                Log.v("Boolean",mResponse.toString());
                // if code is true loggin successfull
                if(mResponse){
                    Intent intent = new Intent(context, AdminMain.class);
                    context.startActivity(intent);
                    // code to next activity
                }
                else Toast.makeText(context, "login failed", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                Toast.makeText(context, "No data available ", Toast.LENGTH_LONG).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params= new HashMap<>();
            params.put("uname",uname);
            params.put("pass",pass);
            return params;
        }
    };
    AppController.getInstance().addToRequestQueue(stringRequest);
}


//get the job data
   public void getJobData(final String status, final String id, final VolleyCallback callback) {
       mList = new ArrayList<DataClass>();
       String mDataFetchUrl = "http://www.avipsr.96.lt/test.php";
       Log.v(".....", "get");

       StringRequest stringRequest = new StringRequest(Request.Method.POST, mDataFetchUrl, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {

                   Log.v("JobData response",response);
                   JSONArray jsonArray = new JSONArray(response);
                   try {
                       for (int i = 0; i < jsonArray.length(); i++) {
                           JSONObject workerJob = jsonArray.getJSONObject(i);
                           String id = workerJob.getString("id");
                           String jobTitle=workerJob.getString("work_title");
                           String jobDetail = workerJob.getString("work_detail");
                           String locationId = workerJob.getString("location_id");
                           String designationId = workerJob.getString("designation_id");
                           DataClass dataClass = new DataClass();
                           dataClass.setJobId(id);
                           dataClass.setJobTitle(jobTitle);
                           dataClass.setJobDes(jobDetail);
                           dataClass.setLocationId(locationId);
                           dataClass.setDesignationId(designationId);
                           mList.add(dataClass);
                           Log.v("mlist", mList.toString());
                           Log.v("......", id + jobDetail);

                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }catch (NullPointerException e){
                       Toast.makeText(context, "No data available ", Toast.LENGTH_LONG).show();
                   }
//
               } catch (JSONException e) {
                   e.printStackTrace();
               }catch (NullPointerException e){
                   Toast.makeText(context, "No data available ", Toast.LENGTH_LONG).show();
               }
               callback.onSuccessResponse(mList);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params= new HashMap<>();
               params.put("status", status);
              params.put("id",id);
               return params;
           }
       };


       AppController.getInstance().addToRequestQueue(stringRequest);

   }

// search the worker for the job
    public void searchWorker(final VolleyCallback callback){
        mList=new ArrayList<DataClass>();
        String mDataFetchUrl = "http://www.avipsr.96.lt/worklist.php";

JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, mDataFetchUrl, null, new Response.Listener<JSONArray>() {
    @Override
    public void onResponse(JSONArray response) {
        Log.v("res code",response.toString());
        Log.v("json length", String.valueOf(response.length()));

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject =response.getJSONObject(i);
                String id=jsonObject.getString("id");
                Log.v("json id",id);
                String name=jsonObject.getString("name");
                String locationId=jsonObject.getString("location_id");
                String designationId=jsonObject.getString("designation_id");
                DataClass dataClass=new DataClass();
                dataClass.setWorkerId(id);
                dataClass.setWorkerName(name);
                dataClass.setLocationId(locationId);
                dataClass.setDesignationId(designationId);
                mList.add(dataClass);

            }
            catch (JSONException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                Toast.makeText(context, "No data available ", Toast.LENGTH_LONG).show();
            }
        }
        callback.onSuccessResponse(mList);
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.v("code",error.getMessage());

    }
});
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
// get the worker for the job
   public void getWorkerData(final String id, String mDataFetchUrl,final VolleyCallback callback){
mList=new ArrayList<>();

       StringRequest stringRequest=new StringRequest(Request.Method.POST, mDataFetchUrl, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Log.v("pending response",response);
               try {
                   JSONArray jsonArray = new JSONArray(response);

                   for (int i=0; i<jsonArray.length();i++){
                       JSONObject jsonObject=jsonArray.getJSONObject(i);
                       String mId=jsonObject.getString("id");
                       Log.v("json id",mId);
                       String name=jsonObject.getString("name");
                       String locationId=jsonObject.getString("location_id");
                       String designationId=jsonObject.getString("designation_id");

                       DataClass dataClass=new DataClass();
                       dataClass.setWorkerId(mId);
                       dataClass.setWorkerName(name);
                       dataClass.setLocationId(locationId);
                       dataClass.setDesignationId(designationId);

                       if(jsonObject.has("work_detail")){
                           String jobData=jsonObject.getString("work_detail");
                           dataClass.setJobData(jobData);
                       }
                       if(jsonObject.has("location")){
                           String location=jsonObject.getString("location");
                           dataClass.setLocation(location);
                       }
                       if(jsonObject.has("job_designation")){
                           String designation=jsonObject.getString("job_designation");
                           dataClass.setDesignation(designation);
                       }
                       mList.add(dataClass);
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }catch (NullPointerException e){
                   Toast.makeText(context, "No data available ", Toast.LENGTH_LONG).show();
               }
               callback.onSuccessResponse(mList);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params= new HashMap<>();
               params.put("id",id);
               return params;
           }
       };
       AppController.getInstance().addToRequestQueue(stringRequest);
   }
}
