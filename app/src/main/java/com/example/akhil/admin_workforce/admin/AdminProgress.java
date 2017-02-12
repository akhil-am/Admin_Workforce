package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.backendAdmin.JobDescription;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.MyAdaptor;
import com.example.akhil.admin_workforce.network.NetworkConnection;

/**
 * Created by akhil on 17/01/17.
 */

public class AdminProgress extends Fragment {
    RecyclerView pendingList;
    RecyclerView.Adapter adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());
    DataClass dataClass=new DataClass();
    Fragment fragment=new JobDescription();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_progress,container,false);
        pendingList = (RecyclerView) view.findViewById(R.id.homelist);
        pendingList.setLayoutManager(new LinearLayoutManager(getContext()));
        networkConnection.getJobData();
        adapter=new MyAdaptor(dataClass.getmList());
        return view;
    }
}
