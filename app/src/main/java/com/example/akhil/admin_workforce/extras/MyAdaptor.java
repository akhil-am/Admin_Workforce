package com.example.akhil.admin_workforce.extras;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 25/01/17.
 */

    public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {
    private List<DataClass> jobData=new ArrayList<>();
    public MyAdaptor(List<DataClass> jobData) {
        this.jobData=jobData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataClass dataclass= jobData.get(position);
        Log.d("jobdata",jobData.toString()+position);
        holder.id.setText(dataclass.getJobId());
        holder.jobTitle.setText(dataclass.getJobTitle());
    }

    @Override
    public int getItemCount() {
        return jobData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;TextView jobTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.jobid);
            jobTitle = (TextView) itemView.findViewById(R.id.jobtitle);
        }
    }
}
