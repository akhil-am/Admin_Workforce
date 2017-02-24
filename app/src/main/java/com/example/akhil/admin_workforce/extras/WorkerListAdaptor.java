package com.example.akhil.admin_workforce.extras;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akhil.admin_workforce.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 17/02/17.
 */

public class WorkerListAdaptor extends RecyclerView.Adapter<WorkerListAdaptor.MyViewHolder>{
    private List<DataClass> workerList=new ArrayList<>();

    public WorkerListAdaptor(List<DataClass> workerList) {
        this.workerList=workerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list,parent,false);
        return new WorkerListAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataClass dataclass= workerList.get(position);
        holder.id.setText(dataclass.getWorkerId());
        holder.jobTitle.setText(dataclass.getWorkerName());
    }

    @Override
    public int getItemCount() {
        return workerList.size();
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
