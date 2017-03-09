package com.example.akhil.admin_workforce.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.backendAdmin.PendingJobDetails;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.DividerItemDecoration;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.extras.JobListAdaptor;
import com.example.akhil.admin_workforce.extras.RecyclerTouchListener;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.List;

/**
 * Created by akhil on 17/01/17.
 */

public class AdminProgress extends Fragment {
    RecyclerView pendingList;
    RecyclerView.Adapter adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());
    DataClass dataClass=new DataClass();
    Fragment fragment=new PendingJobDetails();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_home,container,false);
        pendingList = (RecyclerView) view.findViewById(R.id.homelist);
        pendingList.setLayoutManager(new LinearLayoutManager(getContext()));
        String status="P";
        final ProgressDialog dialog=new ProgressDialog(getActivity());
        dialog.setTitle("please wait....");dialog.show();
        networkConnection.getJobData(status,null, new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List<DataClass> result) {
                dialog.dismiss();
                adapter=new JobListAdaptor(result);

        pendingList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        pendingList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), pendingList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataClass id= result.get(position);
                String mId=id.getJobId();
                String mLocationId=id.getLocationId();
                String mDesignationId=id.getDesignationId();

                Bundle bundle=new Bundle();
                bundle.putString("id",mId);
               // bundle.putString("locationId",mLocationId);
               // bundle.putString("designationId",mDesignationId);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                fragmentInflater.loadFragment();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        pendingList.setAdapter(adapter);
            }
        });
        return view;
    }
}
