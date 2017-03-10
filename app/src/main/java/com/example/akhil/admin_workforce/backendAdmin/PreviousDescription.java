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

/**
 * Created by akhil on 02/03/17.
 */

public class PreviousDescription extends Fragment {
    private String mJobDes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.job_description,container,false);
        TextView textView= (TextView) view.findViewById(R.id.textView4);
        mJobDes=getArguments().getString("jobdes");
        textView.setText(mJobDes);
        Button search= (Button) view.findViewById(R.id.search_worker);
        // use the layout and the search button is disabled
        search.setVisibility(View.GONE);
        return view;
    }
}
