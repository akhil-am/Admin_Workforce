package com.example.akhil.admin_workforce.extras;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 30/01/17.
 */

public class DataClass {
    String jobId;
    String JobTitle,locationId,designationId,workerId,workerName;
     static List<DataClass> mList=new ArrayList<>();

    public String getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getDesignationId() {
        return designationId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public List<DataClass> getmList() {
        return mList;
    }

    public void setmList(List<DataClass> mList) {
        this.mList = mList;
    }
}
