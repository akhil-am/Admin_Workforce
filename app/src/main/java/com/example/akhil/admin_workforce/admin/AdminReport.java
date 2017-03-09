package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.akhil.admin_workforce.R;
import com.example.akhil.admin_workforce.extras.DataClass;
import com.example.akhil.admin_workforce.extras.WorkerListAdaptor;
import com.example.akhil.admin_workforce.network.NetworkConnection;
import com.example.akhil.admin_workforce.network.VolleyCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 17/01/17.
 */

public class AdminReport extends Fragment {

    RecyclerView report;
    WorkerListAdaptor adapter;
    NetworkConnection networkConnection=new NetworkConnection(getContext());
List<DataClass> mResult= new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_home,container,false);
        report = (RecyclerView) view.findViewById(R.id.homelist);
        report.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
       // super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        networkConnection.searchWorker(new VolleyCallback() {
            @Override
            public void onSuccessResponse(List<DataClass> result) {
            mResult=result;
                adapter=new WorkerListAdaptor(result);
                report.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.app_bar_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<DataClass> filterdList = filter(mResult,newText);
                adapter.animateTo(filterdList);
                report.scrollToPosition(0);
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.animateTo(mResult);
                report.scrollToPosition(0);
                return true;
            }
        });

    }
    private List<DataClass> filter(List<DataClass> model,String query){
        query=query.toLowerCase();
        final List<DataClass> filterdList = new ArrayList<>();

        for (int i=0;i<model.size();i++){
            final String text=model.get(i).getWorkerName();
            if (text.contains(query)){
                DataClass data= new DataClass();
                data.setWorkerName(text);
                filterdList.add(data);
            }
        }
        return filterdList;
    }


}
