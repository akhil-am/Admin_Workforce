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
import com.example.akhil.admin_workforce.network.NetworkConnection;

/**
 * Created by akhil on 05/02/17.
 */

public class JobDescription extends Fragment {
    String mLocatioId,mDesignationId,mId;NetworkConnection networkConnection=new NetworkConnection(getContext());
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.job_description,container,false);
        TextView textView= (TextView) view.findViewById(R.id.textView4);
        mId=getArguments().getString("id");
        mLocatioId=getArguments().getString("locationId");
        mDesignationId=getArguments().getString("designationId");
        Button search= (Button) view.findViewById(R.id.search_worker);
        textView.setText(mId);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkConnection.searchWorker();

            }
        });
        return view;
    }
}
