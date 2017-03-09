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
       // holder.id.setText(dataclass.getWorkerId());
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
           // id = (TextView) itemView.findViewById(R.id.jobid);
            jobTitle = (TextView) itemView.findViewById(R.id.jobtitle);
        }
    }



      /** Filter Logic
     **/
    public void animateTo(List<DataClass> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);

    }

    private void applyAndAnimateRemovals(List<DataClass> newModels) {

        for (int i = workerList.size() - 1; i >= 0; i--) {
            final DataClass model = workerList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<DataClass> newModels) {

        for (int i = 0, count = newModels.size(); i < count; i++) {
            final DataClass model = newModels.get(i);
            if (!newModels.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void applyAndAnimateMovedItems(List<DataClass> newModels) {

        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final DataClass model = newModels.get(toPosition);
            final int fromPosition = workerList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public DataClass removeItem(int position) {
        final DataClass model = workerList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, DataClass model) {
        workerList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final DataClass model = workerList.remove(fromPosition);
        workerList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}


