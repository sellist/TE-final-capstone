package com.techelevator.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class AssignWorkerDto {

    @NotEmpty
    @Positive
    private int workerId;
    @Positive
    @NotEmpty
    private int maintenanceId;

    public AssignWorkerDto(int workerId, int maintenanceId) {
        this.workerId = workerId;
        this.maintenanceId = maintenanceId;
    }

    public AssignWorkerDto() {
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    @Override
    public String toString() {
        return "AssignWorkerDto{" +
                "workerId=" + workerId +
                ", maintenanceId=" + maintenanceId +
                '}';
    }
}
