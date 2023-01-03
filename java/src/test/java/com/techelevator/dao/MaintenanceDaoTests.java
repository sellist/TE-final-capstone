package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.techelevator.model.MaintenanceRequest;
import com.techelevator.model.User;
import com.techelevator.model.dto.AssignWorkerDto;
import com.techelevator.model.dto.MaintenanceRequestDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public class MaintenanceDaoTests extends BaseDaoTests{

    private JdbcMaintenanceDao sut;
    private MaintenanceRequestDto MAINT_REQ_DTO;
    private AssignWorkerDto ASS_WORK_DTO;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMaintenanceDao(jdbcTemplate);
        ASS_WORK_DTO = new AssignWorkerDto(1,1);
        MAINT_REQ_DTO = new MaintenanceRequestDto(1,"test","test","test");
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

    @Test
    public void getRequestById_returns_object() {
        Assert.assertNotNull(sut.getRequestById(1));
        MaintenanceRequest returned = sut.getRequestById(1);
        Assert.assertEquals(returned.getPropertyId(),1);
    }

    @Test
    public void date_default_created_when_created() {
        MaintenanceRequest returned = sut.createMaintenanceRequest(MAINT_REQ_DTO);
        Assert.assertEquals(returned.getRequestDate(), Date.valueOf(LocalDate.now()));
    }

    @Test
    public void getRequestId_given_invalid_id_returns_empty_request() {
        MaintenanceRequest req = sut.getRequestById(-1);

        maintenanceCompare(req, new MaintenanceRequest());
    }


    @Test
    public void gets_requests_by_property_id(){
        List<MaintenanceRequest> returned = sut.getRequestsByPropertyId(1);
        Assert.assertEquals(1,returned.size());
    }

    @Test
    public void getRequestByPropertyId_given_invalid_id_returns_empty_list() {
        List<MaintenanceRequest> req = sut.getRequestsByWorkerId(-1);

        Assert.assertEquals(req, new ArrayList<>());
    }

    @Test
    public void gets_requests_by_worker_id(){
        List<MaintenanceRequest> returned = sut.getRequestsByWorkerId(1);
        Assert.assertEquals(returned.size(), 0);

    }

    @Test
    public void getRequestByWorkerId_given_invalid_user_id_returns_empty_list() {
        List<MaintenanceRequest> req = sut.getRequestsByWorkerId(-1);

        Assert.assertEquals(req, new ArrayList<>());
    }

    @Test
    public void gets_requests_by_landlord_id() {
        List<MaintenanceRequest> returned = sut.getRequestsByLandlordId(2);
        Assert.assertEquals(0, returned.size());
    }

    @Test
    public void getRequestByLandlordId_given_invalid_id_returns_empty_list() {
        List<MaintenanceRequest> req = sut.getRequestsByLandlordId(-1);

        Assert.assertEquals(req, new ArrayList<>());
    }

    @Test
    public void maintenance_request_entry_created() {
        MaintenanceRequest returned = sut.createMaintenanceRequest(MAINT_REQ_DTO);
        maintenanceCompare(
                sut.getRequestById(returned.getRequestId()),
                returned
        );
    }

    @Test
    public void can_assign_workers_to_maint_requests() {
        Assert.assertEquals(sut.getRequestsByWorkerId(1).size(),0);
        Integer createdId = sut.createMaintenanceRequest(MAINT_REQ_DTO).getRequestId();
        ASS_WORK_DTO.setMaintenanceId(createdId);
        ASS_WORK_DTO.setWorkerId(1);
        sut.assignWorkerToMaintenanceRequest(ASS_WORK_DTO);
        Assert.assertEquals(sut.getRequestsByWorkerId(1).size(),1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void invalid_maintenance_request_id_throws_db_exception() {
        MAINT_REQ_DTO.setPropertyId(-1);
        MaintenanceRequest returned = sut.createMaintenanceRequest(MAINT_REQ_DTO);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void invalid_maintenance_request_desc_throws_db_exception() {
        MAINT_REQ_DTO.setDescription(null);
        MaintenanceRequest returned = sut.createMaintenanceRequest(MAINT_REQ_DTO);
    }

    @Test
    public void updates_maintenance_request() {
        MaintenanceRequest returned = sut.getRequestById(1);
        returned.setWorkerId(null);
        returned.setDescription("this is a test sentence...no one should see this.........");
        Assert.assertEquals(1, sut.updateMaintenanceRequest(1,returned));
    }

    @Test
    public void deletes_maintenance_request() {
        Assert.assertEquals(1, sut.deleteMaintenanceRequest(1));
    }

    private void maintenanceCompare(MaintenanceRequest m1, MaintenanceRequest m2) {
        Assert.assertEquals(m1.getPropertyId(), m2.getPropertyId());
        Assert.assertEquals(m1.getRequestId(), m2.getRequestId());
        Assert.assertEquals(m1.getWorkerId(), m2.getWorkerId());
        Assert.assertEquals(m1.isCompleted(), m2.isCompleted());
        Assert.assertEquals(m1.getCompletedDate(), m2.getCompletedDate());
        Assert.assertEquals(m1.getCategory(), m2.getCategory());
        Assert.assertEquals(m1.getRequestDate(), m2.getRequestDate());
        Assert.assertEquals(m1.getTitle(), m2.getTitle());
        Assert.assertEquals(m1.getDescription(), m2.getDescription());

    }
}
