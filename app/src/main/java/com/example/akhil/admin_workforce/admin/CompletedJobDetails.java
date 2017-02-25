package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.List;

/**
 * Created by akhil on 26/02/17.
 */
public class CompletedJobDetails extends Fragment {
    NetworkConnection networkConnection=new NetworkConnection(getContext());String mUrl="http://avipsr.96.lt/pendingdetail.php";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.completed_job_detail,container,false);
        String mid=getArguments().getString("id");
        networkConnection.getWorkerData(mid, mUrl, new VolleyCallback() {
            @Override
            public void onSuccessResponse(List<DataClass> result) {
                Log.v("size of result", String.valueOf(result.size()));
                DataClass data=result.get(0);

                TextView jobData= (TextView) view.findViewById(R.id.c_Job_detail);
                TextView workerId= (TextView) view.findViewById(R.id.c_client_id);
                TextView workerName= (TextView) view.findViewById(R.id.c_name);
                TextView workerLoc= (TextView) view.findViewById(R.id.c_location);
                TextView workerDes= (TextView) view.findViewById(R.id.c_designation);
                jobData.setText(data.getJobData());
                workerId.setText(data.getWorkerId());
                workerName.setText(data.getWorkerName());
                workerLoc.setText(data.getLocationId());
                workerDes.setText(data.getDesignationId());
                Log.v("details pending",data.getJobData()+data.getWorkerId()+data.getWorkerName()+data.getLocationId()+data.getDesignationId());
            }
        });
        return view;
    }
}
