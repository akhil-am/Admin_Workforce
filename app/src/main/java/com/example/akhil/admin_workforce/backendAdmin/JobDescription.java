package com.example.akhil.admin_workforce.backendAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.network.NetworkConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 05/02/17.
 */

public class JobDescription extends Fragment {
    String mLocationId,mDesignationId,mId;NetworkConnection networkConnection=new NetworkConnection(getContext());DataClass dataClass=new DataClass();
    DataClass mData=new DataClass(); List<DataClass> dummy=new ArrayList<DataClass>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.job_description,container,false);
        TextView textView= (TextView) view.findViewById(R.id.textView4);
        mId=getArguments().getString("id");
        mLocationId=getArguments().getString("locationId");
        mDesignationId=getArguments().getString("designationId");
        Button search= (Button) view.findViewById(R.id.search_worker);
            textView.setText(mId);
        networkConnection.searchWorker();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                DataClass data=new DataClass();
                for (int i =0;i<dataClass.getwList().size();i++){
                    data=dataClass.getwList().get(i);

                    Log.v("data", String.valueOf(dataClass.getwList().get(i).getWorkerId()));
                    Log.v("mid",mId);
                    Log.v("mlocation",mDesignationId);
                    Log.v("mdes",mDesignationId);

                    if (data.getLocationId().equals(mLocationId)&&data.getDesignationId().equals(mDesignationId)) {

                        String id = data.getWorkerId();
                        String name = data.getWorkerName();
                        Log.v("id",id);
                   Log.v("id",name);
                        mData.setWorkerId(id);
                                            mData.setWorkerName(name);
                        dummy.add(mData);
                    }
                                            dataClass.setDummy(dummy);
                       // bundle.putCharSequenceArrayList("list",dummy);
                        Fragment fragment=new WorkerList();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                        fragmentInflater.loadFragment();


                }

            }
        });
        return view;
    }
}
