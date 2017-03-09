package com.example.akhil.admin_workforce.backendAdmin;

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
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.DividerItemDecoration;
import com.example.akhil.admin_workforce.extras.FragmentInflater;
import com.example.akhil.admin_workforce.extras.JobListAdaptor;
import com.example.akhil.admin_workforce.extras.RecyclerTouchListener;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.List;

/**
 * Created by akhil on 08/03/17.
 */

public class PreviousJob extends Fragment {
    RecyclerView previous;
    RecyclerView.Adapter adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_home,container,false);
        previous = (RecyclerView) view.findViewById(R.id.homelist);
        previous.setLayoutManager(new LinearLayoutManager(getContext()));
        networkConnection.getJobData("C", getArguments().getString("id"), new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List<DataClass> result) {
                adapter=new JobListAdaptor(result);
                previous.setAdapter(adapter);
                previous.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                previous.addOnItemTouchListener(new RecyclerTouchListener(getContext(), previous, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        DataClass id= result.get(position);
                        String mJobDes=id.getJobDes();
                        Bundle bundle=new Bundle();
                        Fragment fragment=new PreviousJob();
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
