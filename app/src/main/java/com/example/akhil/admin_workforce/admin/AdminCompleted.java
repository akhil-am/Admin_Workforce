package com.example.akhil.admin_workforce.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akhil.admin_workforce.R;

/**
 * Created by akhil on 17/01/17.
 */

public class AdminCompleted extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(".........","Oncreatview");
        View view=inflater.inflate(R.layout.admin_completed,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       Log.v("........","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(".........","onCreat");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("......","onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(".....","onStop");
    }

}
