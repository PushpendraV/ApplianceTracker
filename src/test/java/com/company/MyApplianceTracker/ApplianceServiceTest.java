package com.company.MyApplianceTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.company.appliancemanagement.model.Appliance;
import com.company.appliancemanagement.repository.ApplianceRepository;
import com.company.appliancemanagement.service.ApplianceService;

public class ApplianceServiceTest {

    @Mock
    private ApplianceRepository applianceRepository;

    @InjectMocks
    private ApplianceService applianceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAppliances() {
        // Prepare test data
        List<Appliance> appliances = new ArrayList<>();
        appliances.add(new Appliance(1L, "Serial1", "Brand1", "Model1", new Date(), "Active"));
        appliances.add(new Appliance(2L, "Serial2", "Brand2", "Model2", new Date(), "Inactive"));

        // Define the mock behavior
        when(applianceRepository.findAll()).thenReturn(appliances);

        // Call the service method
        List<Appliance> result = applianceService.getAllAppliances();

        // Verify the result
        assertEquals(appliances.size(), result.size());
        assertEquals(appliances.get(0).getBrand(), result.get(0).getBrand());
        assertEquals(appliances.get(1).getModel(), result.get(1).getModel());
    }

    @Test
    public void testGetApplianceById() {
        // Prepare test data
        Appliance appliance = new Appliance(1L, "Serial1", "Brand1", "Model1", new Date(), "Active");

        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(appliance));

        // Call the service method
        Appliance result = applianceService.getApplianceById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals(appliance.getSerialNumber(), result.getSerialNumber());
        assertEquals(appliance.getDateBought(), result.getDateBought());
    }

    @Test
    public void testGetApplianceById_NonExistentId() {
        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Appliance result = applianceService.getApplianceById(1L);

        // Verify the result
        assertNull(result);
    }

    @Test
    public void testCreateAppliance() {
        // Prepare test data
        Appliance newAppliance = new Appliance(null, "Serial3", "Brand3", "Model3", new Date(), "Active");
        Appliance savedAppliance = new Appliance(3L, "Serial3", "Brand3", "Model3", new Date(), "Active");

        // Define the mock behavior
        when(applianceRepository.save(newAppliance)).thenReturn(savedAppliance);

        // Call the service method
        Appliance result = applianceService.createAppliance(newAppliance);

        // Verify the result
        assertNotNull(result);
        assertEquals(savedAppliance.getId(), result.getId());
        assertEquals(savedAppliance.getBrand(), result.getBrand());
    }

    @Test
    public void testUpdateAppliance() {
        // Prepare test data
        Appliance existingAppliance = new Appliance(1L, "Serial1", "Brand1", "Model1", new Date(), "Active");
        Appliance updatedAppliance = new Appliance(1L, "Serial1_New", "Brand1_New", "Model1_New", new Date(), "Inactive");

        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(existingAppliance));
        when(applianceRepository.save(existingAppliance)).thenReturn(updatedAppliance);

        // Call the service method
        Appliance result = applianceService.updateAppliance(1L, updatedAppliance);

        // Verify the result
        assertNotNull(result);
        assertEquals(updatedAppliance.getSerialNumber(), result.getSerialNumber());
        assertEquals(updatedAppliance.getStatus(), result.getStatus());
    }

    @Test
    public void testUpdateAppliance_NonExistentId() {
        // Prepare test data
        Appliance updatedAppliance = new Appliance(1L, "Serial1_New", "Brand1_New", "Model1_New", new Date(), "Inactive");

        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Appliance result = applianceService.updateAppliance(1L, updatedAppliance);

        // Verify the result
        assertNull(result);
    }

    @Test
    public void testDeleteAppliance() {
        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(new Appliance()));

        // Call the service method
        boolean result = applianceService.deleteAppliance(1L);

        // Verify the result
        assertTrue(result);
        verify(applianceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAppliance_NonExistentId() {
        // Define the mock behavior
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        boolean result = applianceService.deleteAppliance(1L);

        // Verify the result
        assertFalse(result);
        verify(applianceRepository, never()).deleteById(anyLong());
    }
}
