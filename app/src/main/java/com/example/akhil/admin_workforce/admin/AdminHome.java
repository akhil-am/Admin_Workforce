package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
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

/**
 * Created by akhil on 13/01/17.
 */

public class AdminHome extends Fragment {
    RecyclerView homeList;
    RecyclerView.Adapter adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());
    DataClass dataClass=new DataClass();
    Fragment fragment=new JobDescription();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_home,container,false);

      //  RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getContext());
        homeList = (RecyclerView) view.findViewById(R.id.homelist);
        homeList.setLayoutManager(new LinearLayoutManager(getContext()));
        String status="A";
        networkConnection.getJobData(status);
        adapter=new JobListAdaptor(dataClass.getmList());
        Log.d("adaptor....", String.valueOf(adapter.getItemCount()));
        homeList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        homeList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), homeList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataClass id= dataClass.getmList().get(position);
                String mId=id.getJobId();
                String mLocationId=id.getLocationId();
                String mDesignationId=id.getDesignationId();
//                Intent intent=new Intent(getActivity(), JobDescription.class);
//                intent.putExtra("id",mId);
//                startActivity(intent);
                Bundle bundle=new Bundle();
                bundle.putString("id",mId);
                bundle.putString("locationId",mLocationId);
                bundle.putString("designationId",mDesignationId);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                fragmentInflater.loadFragment();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        homeList.setAdapter(adapter);
    
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
