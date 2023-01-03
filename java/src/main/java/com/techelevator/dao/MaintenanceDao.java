package com.techelevator.dao;

import com.techelevator.model.MaintenanceRequest;
import com.techelevator.model.dto.MaintenanceRequestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface MaintenanceDao {

    MaintenanceRequest getRequestById(int requestId);

    List<MaintenanceRequest> getRequestsByPropertyId(int propertyId);

    List<MaintenanceRequest> getRequestsByWorkerId(int workerId);

    List<MaintenanceRequest> getRequestsByLandlordId(int landlordId);

    MaintenanceRequest createMaintenanceRequest(MaintenanceRequestDto requestDto);

    int updateMaintenanceRequest(int id, MaintenanceRequest maintenanceRequest);

    int deleteMaintenanceRequest(int requestId);

}
