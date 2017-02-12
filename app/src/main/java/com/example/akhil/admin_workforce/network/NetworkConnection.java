package com.example.akhil.admin_workforce.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.akhil.admin_workforce.extras.DataClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 26/01/17.
 */

public class NetworkConnection {
    private Context context;
static List<DataClass> mList;
   public NetworkConnection(Context context){
        this.context=context;

    }
//    ProgressDialog pd=new ProgressDialog(context);
private  void login(){
   // pd.setMessage("please wait logging");pd.show();
    String mLoginUrl = "";
    JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, mLoginUrl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
//pd.hide();
            try {
                Boolean mResponse= response.getBoolean("code");
                if(mResponse){
                    // code to next activity
                }
                else Toast.makeText(context, "login failed", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {

                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });

}

   public void getJobData(){
       mList=new ArrayList<DataClass>();
     //  pd.setMessage("Fetching data..");
      // pd.show();
       String mDataFetchUrl = "http://www.avipsr.96.lt/test.php";
       Log.v(".....","get");





       JsonArrayRequest jsonArrayReq= new JsonArrayRequest(Request.Method.GET, mDataFetchUrl, null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
             //  pd.dismiss();
               Log.v(".......",response.toString());
for(int i=0;i<response.length();i++){
   try {
        JSONObject workerJob=response.getJSONObject(i);
        String id= workerJob.getString("id");
        String job=workerJob.getString("work_detail");
       String locationId=workerJob.getString("location_id");
       String designationId=workerJob.getString("designation_id");
        DataClass dataClass=new DataClass();
        dataClass.setJobId(id);
        dataClass.setJobTitle(job);
       dataClass.setLocationId(locationId);
       dataClass.setDesignationId(designationId);
        mList.add(dataClass);
       Log.v("mlist",mList.toString());
        Log.v("......",id+job);
        DataClass da=new DataClass();
        da.setmList(mList);

    } catch (JSONException e) {
        e.printStackTrace();
    }

}

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
       AppController.getInstance().addToRequestQueue(jsonArrayReq);
      // return mList;

   }

    public void searchWorker(){
        mList=new ArrayList<DataClass>();
        String mDataFetchUrl = "http://www.avipsr.96.lt/workerlist.php";
        Log.v(".....","list");
JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, mDataFetchUrl, null, new Response.Listener<JSONArray>() {
    @Override
    public void onResponse(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject =response.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                DataClass dataClass=new DataClass();
                dataClass.setWorkerId(id);
                dataClass.setWorkerName(name);
                mList.add(dataClass);
                dataClass.setmList(mList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

    }
});
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
