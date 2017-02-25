package com.example.akhil.admin_workforce.backendAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.List;

/**
 * Created by akhil on 19/02/17.
 */

public class WorkerDescription extends Fragment {
    private NetworkConnection networkConnection=new NetworkConnection(getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.worker_detail,container,false);
        networkConnection.getWorkerData(getArguments().getString("id"), null, new VolleyCallback() {
            @Override
            public void onSuccessResponse(List<DataClass> result) {

            }
        });
        return view;
    }
}
