package com.example.akhil.admin_workforce.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.backendAdmin.JobDescription;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.DividerItemDecoration;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.extras.JobListAdaptor;
import com.example.akhil.admin_workforce.extras.RecyclerTouchListener;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.List;

/**
 * Created by akhil on 13/01/17.
 */

public class AdminHome extends Fragment {
   RecyclerView homeList;
    RecyclerView.Adapter adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());
    Fragment fragment=new JobDescription();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_home,container,false);

        homeList = (RecyclerView) view.findViewById(R.id.homelist);
        homeList.setLayoutManager(new GridLayoutManager(getContext(),1));
        // getting Available jobs as status A
        String status="A";
       final ProgressDialog dialog=new ProgressDialog(getActivity());
        dialog.setMessage("please wait....");dialog.show();
        networkConnection.getJobData(status,"0",new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List<DataClass> result) {
                dialog.dismiss();
                adapter=new JobListAdaptor(result);
                Log.d("adaptor....", String.valueOf(adapter.getItemCount()));



                homeList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                homeList.setItemAnimator(new DefaultItemAnimator());
                homeList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), homeList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataClass id= result.get(position);
                String mId=id.getJobId();
                String mLocationId=id.getLocationId();
                String mDesignationId=id.getDesignationId();
                String mJobDes=id.getJobDes();
                Bundle bundle=new Bundle();

                //Set the data and pass to next fragment job description

                bundle.putString("id",mId);
                bundle.putString("jobdes",mJobDes);
                bundle.putString("locationId",mLocationId);
                bundle.putString("designationId",mDesignationId);
                fragment.setArguments(bundle);

                //fragment inflator
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                fragmentInflater.loadFragment();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        homeList.setAdapter(adapter);
            }
        });
        return view;
    }

}
