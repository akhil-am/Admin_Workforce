package com.example.akhil.admin_workforce.backendAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        final View view= inflater.inflate(R.layout.worker_detail,container,false);
        networkConnection.getWorkerData(getArguments().getString("id"), null, new VolleyCallback() {
            @Override
            public void onSuccessResponse(List<DataClass> result) {
                DataClass data=result.get(0);
                TextView clientId= (TextView) view.findViewById(R.id.a_client_id);
                TextView clientName= (TextView) view.findViewById(R.id.a_name);
                TextView clientDesignation= (TextView) view.findViewById(R.id.a_designation);
                TextView clientLocation= (TextView) view.findViewById(R.id.a_location);
                Button previous= (Button) view.findViewById(R.id.a_previous);
                Button assign= (Button) view.findViewById(R.id.a_assign);

                clientId.setText(data.getWorkerId());
                clientName.setText(data.getWorkerName());
                clientDesignation.setText(data.getDesignationId());
                clientLocation.setText(data.getLocationId());
                previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                assign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

        return view;
    }
}
