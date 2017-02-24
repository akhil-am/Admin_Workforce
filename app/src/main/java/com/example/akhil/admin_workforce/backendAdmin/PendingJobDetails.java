package com.example.akhil.admin_workforce.backendAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.network.NetworkConnection;

/**
 * Created by akhil on 23/02/17.
 */

public class PendingJobDetails extends Fragment {
    NetworkConnection networkConnection=new NetworkConnection(getContext());String mUrl="http://avipsr.96.lt/pendingdetail.php";
    DataClass dataClass=new DataClass();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mid=getArguments().getString("id");
        networkConnection.getWorkerData(mid,mUrl);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataClass data=dataClass.getmList().get(0);
        View view=inflater.inflate(R.layout.pending_job_detail,container,false);
        TextView jobData= (TextView) view.findViewById(R.id.wd_Job_detail);
        TextView workerId= (TextView) view.findViewById(R.id.wd_client_id);
        TextView workerName= (TextView) view.findViewById(R.id.wd_name);
        TextView workerLoc= (TextView) view.findViewById(R.id.wd_location);
        TextView workerDes= (TextView) view.findViewById(R.id.wd_designation);
        jobData.setText(data.getJobData());
        workerId.setText(data.getWorkerId());
        workerName.setText(data.getWorkerName());
        workerLoc.setText(data.getLocationId());
        workerDes.setText(data.getDesignationId());

        return view;
    }
}
