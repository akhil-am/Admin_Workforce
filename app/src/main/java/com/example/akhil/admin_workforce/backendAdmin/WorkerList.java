package com.example.akhil.admin_workforce.backendAdmin;

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
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.DividerItemDecoration;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.extras.RecyclerTouchListener;
import com.example.akhil.admin_workforce.extras.WorkerListAdaptor;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 09/02/17.
 */

public class WorkerList extends Fragment {
    RecyclerView workList;
    RecyclerView.Adapter adapter;
    DataClass dataClass=new DataClass();
    NetworkConnection networkConnection=new NetworkConnection(getContext());
    private  String mLocationId;
    private String mDesignationId;
    //List<DataClass> workerList= new ArrayList<>();
    DataClass mData=new DataClass(); List<DataClass> dummy;
    Bundle bundle= new Bundle();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.worker_list,container,false);
        workList = (RecyclerView) view.findViewById(R.id.workerlist);
        workList.setLayoutManager(new LinearLayoutManager(getContext()));

        mLocationId=getArguments().getString("locationId");
        mDesignationId=getArguments().getString("designationId");
        Log.v("mLocationId",mLocationId);
        Log.v("mDesignationId",mDesignationId);
        networkConnection.searchWorker(new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List<DataClass> result) {

                dummy=new ArrayList<DataClass>();
                DataClass data=new DataClass();
                for (int i =0;i<result.size();i++){
                    data=result.get(i);

                   Log.v("data", String.valueOf(result.get(i).getWorkerId()));
//                    Log.v("mid",mId);
//                    Log.v("mlocation",mDesignationId);
//                    Log.v("mdes",mDesignationId);

                    if ((data.getLocationId().equals(mLocationId))&&(data.getDesignationId().equals(mDesignationId))) {

                        String id = data.getWorkerId();
                        String name = data.getWorkerName();
                        Log.v("id",id);
                        Log.v("name",name);
                        mData.setWorkerId(id);
                        mData.setWorkerName(name);
                        dummy.add(mData);
                    }
                    // dataClass.setDummy(dummy);
                    // bundle.putCharSequenceArrayList("list",dummy);



                }






        adapter=new WorkerListAdaptor(dummy);
        workList.setAdapter(adapter);
        workList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        workList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), workList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataClass id= result.get(position);
            String mid=id.getWorkerId();
               bundle.putString("id",mid);
                Fragment fragment=new WorkerDescription();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentInflater fragmentInflater=new FragmentInflater(getContext(), fragment,fragmentManager);
                fragmentInflater.loadFragment();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
            }
        });
        return view;
    }
}
