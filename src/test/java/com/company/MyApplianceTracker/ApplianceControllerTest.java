package com.company.MyApplianceTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.appliancemanagement.controller.ApplianceController;
import com.company.appliancemanagement.model.Appliance;
import com.company.appliancemanagement.service.ApplianceService;

class ApplianceControllerTest {

    private ApplianceController applianceController;
    private ApplianceService applianceService;

    @BeforeEach
    void setUp() {
        applianceService = mock(ApplianceService.class);
        applianceController = new ApplianceController(applianceService);
    }

    @Test
    void testGetAllAppliances() {
        // Create a list of appliances for the mock response
        List<Appliance> mockAppliances = new ArrayList<>();
        mockAppliances.add(new Appliance(1L, "Appliance 1", "Category 1", null, null, null));
        mockAppliances.add(new Appliance(2L, "Appliance 2", "Category 2", null, null, null));

        // Mock the service method call
        when(applianceService.getAllAppliances()).thenReturn(mockAppliances);

        // Call the controller method
        ResponseEntity<List<Appliance>> responseEntity = applianceController.getAllAppliances();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAppliances, responseEntity.getBody());
    }

    @Test
    void testGetApplianceById() {
        // Create a mock appliance for the mock response
        Appliance mockAppliance = new Appliance(1L, "Appliance 1", "Category 1", null, null, null);

        // Mock the service method call
        when(applianceService.getApplianceById(1L)).thenReturn(mockAppliance);

        // Call the controller method
        ResponseEntity<Appliance> responseEntity = applianceController.getApplianceById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAppliance, responseEntity.getBody());
    }

    @Test
    void testGetApplianceById_NotFound() {
        // Mock the service method call to return null, simulating a not found scenario
        when(applianceService.getApplianceById(1L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Appliance> responseEntity = applianceController.getApplianceById(1L);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testCreateAppliance() {
        // Create a mock appliance for the request body
        Appliance requestAppliance = new Appliance(null, "New Appliance", "New Category", null, null, null);

        // Create a mock appliance for the response
        Appliance mockAppliance = new Appliance(1L, "New Appliance", "New Category", null, null, null);

        // Mock the service method call
        when(applianceService.createAppliance(requestAppliance)).thenReturn(mockAppliance);

        // Call the controller method
        ResponseEntity<Appliance> responseEntity = applianceController.createAppliance(requestAppliance);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockAppliance, responseEntity.getBody());
    }

    @Test
    void testUpdateAppliance() {
        // Create a mock appliance for the request body
        Appliance requestAppliance = new Appliance(null, "Updated Appliance", "Updated Category", null, null, null);

        // Create a mock appliance for the response
        Appliance mockAppliance = new Appliance(1L, "Updated Appliance", "Updated Category", null, null, null);

        // Mock the service method call
        when(applianceService.updateAppliance(1L, requestAppliance)).thenReturn(mockAppliance);

        // Call the controller method
        ResponseEntity<Appliance> responseEntity = applianceController.updateAppliance(1L, requestAppliance);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAppliance, responseEntity.getBody());
    }

    @Test
    void testUpdateAppliance_NotFound() {
        // Create a mock appliance for the request body
        Appliance requestAppliance = new Appliance(null, "Updated Appliance", "Updated Category", null, null, null);

        // Mock the service method call to return null, simulating a not found scenario
        when(applianceService.updateAppliance(1L, requestAppliance)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Appliance> responseEntity = applianceController.updateAppliance(1L, requestAppliance);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testDeleteAppliance() {
        // Mock the service method call to indicate successful deletion
        when(applianceService.deleteAppliance(1L)).thenReturn(true);

        // Call the controller method
        ResponseEntity<Void> responseEntity = applianceController.deleteAppliance(1L);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testDeleteAppliance_NotFound() {
        // Mock the service method call to indicate unsuccessful deletion
        when(applianceService.deleteAppliance(1L)).thenReturn(false);

        // Call the controller method
        ResponseEntity<Void> responseEntity = applianceController.deleteAppliance(1L);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
