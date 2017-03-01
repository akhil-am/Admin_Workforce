package com.example.akhil.admin_workforce.backendAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.network.NetworkConnection;

/**
 * Created by akhil on 05/02/17.
 */

public class JobDescription extends Fragment {
    String mLocationId,mDesignationId,mId,mJobDes;NetworkConnection networkConnection=new NetworkConnection(getContext());DataClass dataClass=new DataClass();
   Bundle bundle=new Bundle();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.job_description,container,false);
        TextView textView= (TextView) view.findViewById(R.id.textView4);
        mId=getArguments().getString("id");
        mLocationId=getArguments().getString("locationId");
        mDesignationId=getArguments().getString("designationId");
        mJobDes=getArguments().getString("jobdes");
        Button search= (Button) view.findViewById(R.id.search_worker);
            textView.setText(mJobDes);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new WorkerList();
                bundle.putString("id",mId);
                bundle.putString("locationId",mLocationId);
                bundle.putString("designationId",mDesignationId);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                fragmentInflater.loadFragment();

            }
        });

        return view;
    }
}
