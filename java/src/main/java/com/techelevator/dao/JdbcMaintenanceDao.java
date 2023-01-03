package com.techelevator.dao;

import com.techelevator.model.Invoice;
import com.techelevator.model.MaintenanceRequest;
import com.techelevator.model.dto.AssignWorkerDto;
import com.techelevator.model.dto.MaintenanceRequestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMaintenanceDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MaintenanceRequest getRequestById(int requestId) {
        String sql = "SELECT request_id, worker_id, property_id, title, description, category, request_date, complete_date " +
                     "FROM maintenance_request " +
                     "WHERE request_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, requestId);

        MaintenanceRequest request = new MaintenanceRequest();

        if (rowSet.next()) {
            request = mapRowToRequest(rowSet);
        }

        return request;
    }

    public List<MaintenanceRequest> getRequestsByPropertyId(int propertyId) {
        String sql = "SELECT request_id, worker_id, property_id, title, description, category, request_date, complete_date " +
                "FROM maintenance_request " +
                "WHERE property_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, propertyId);

        List<MaintenanceRequest> requests = new ArrayList<>();

        while (rowSet.next()) {
           requests.add(mapRowToRequest(rowSet));
        }

        return requests;
    }

    public List<MaintenanceRequest> getRequestsByWorkerId(int workerId) {
        String sql = "SELECT request_id, worker_id, property_id, title, description, category, request_date, complete_date " +
                "FROM maintenance_request " +
                "WHERE worker_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, workerId);

        List<MaintenanceRequest> requests = new ArrayList<>();

        while (rowSet.next()) {
            requests.add(mapRowToRequest(rowSet));
        }

        return requests;
    }

    public List<MaintenanceRequest> getRequestsByLandlordId(int landlordId) {
        String sql = "SELECT request_id, worker_id, maintenance_request.property_id, maintenance_request.title, maintenance_request.description, maintenance_request.category, request_date, complete_date " +
                     "FROM maintenance_request " +
                     "JOIN property ON maintenance_request.property_id = property.property_id " +
                     "WHERE landlord_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, landlordId);

        List<MaintenanceRequest> requests = new ArrayList<>();

        while (rowSet.next()) {
            requests.add(mapRowToRequest(rowSet));
        }

        return requests;
    }

    public MaintenanceRequest createMaintenanceRequest(MaintenanceRequestDto requestDto) {
        String sql = "INSERT INTO maintenance_request (property_id, title, description, category)" +
                     "VALUES (?, ?, ?, ?) " +
                     "RETURNING request_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                                                    requestDto.getPropertyId(),
                                                    requestDto.getTitle(),
                                                    requestDto.getDescription(),
                                                    requestDto.getCategory());
        if (newId != null) {
            return getRequestById(newId);
        }
        return new MaintenanceRequest();
    }

    public void assignWorkerToMaintenanceRequest(AssignWorkerDto workerDto){
        String sql =
                "UPDATE maintenance_request " +
                "SET worker_id = ? " +
                "WHERE request_id = ?;";

        jdbcTemplate.update(
                sql,
                workerDto.getWorkerId(),
                workerDto.getMaintenanceId()
        );
    }

    public int updateMaintenanceRequest(int id, MaintenanceRequest maintenanceRequest) {
        String sql = "UPDATE maintenance_request " +
                     "SET worker_id = ?, property_id = ?, description = ?, category = ?, request_date = ?, complete_date = ? " +
                     "WHERE request_id = ?;";
        return jdbcTemplate.update(sql, maintenanceRequest.getWorkerId(),
                            maintenanceRequest.getPropertyId(),
                            maintenanceRequest.getDescription(),
                            maintenanceRequest.getCategory(),
                            maintenanceRequest.getRequestDate(),
                            maintenanceRequest.getCompletedDate(),
                            id);
    }

    public int deleteMaintenanceRequest(int requestId) {
        String sql = "DELETE FROM maintenance_request WHERE request_id = ?;";
        return jdbcTemplate.update(sql, requestId);
    }

    private MaintenanceRequest mapRowToRequest(SqlRowSet rowset) {
        MaintenanceRequest request = new MaintenanceRequest();

        request.setRequestId(rowset.getInt("request_id"));
        request.setWorkerId(rowset.getInt("worker_id"));
        request.setPropertyId(rowset.getInt("property_id"));
        request.setTitle(rowset.getString("title"));
        request.setDescription(rowset.getString("description"));
        request.setCategory(rowset.getString("category"));
        request.setRequestDate(rowset.getDate("request_date"));
        request.setCompletedDate(rowset.getDate("complete_date"));

        return request;
    }

}
