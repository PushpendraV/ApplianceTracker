package com.company.appliancemanagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.appliancemanagement.model.Appliance;
import com.company.appliancemanagement.service.ApplianceService;

@RestController
@RequestMapping("/api/appliances")
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular frontend's URL
public class ApplianceController {

    private final ApplianceService applianceService;

    @Autowired
    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    // Endpoint to get a list of all appliances
    @GetMapping
    public ResponseEntity<List<Appliance>> getAllAppliances() {
        List<Appliance> appliances = applianceService.getAllAppliances();
        return new ResponseEntity<>(appliances, HttpStatus.OK);
    }

    // Endpoint to get a specific appliance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appliance> getApplianceById(@PathVariable Long id) {
        Appliance appliance = applianceService.getApplianceById(id);
        if (appliance != null) {
            return new ResponseEntity<>(appliance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new appliance record
    @PostMapping
    public ResponseEntity<Appliance> createAppliance(@RequestBody Appliance appliance) {
        Appliance createdAppliance = applianceService.createAppliance(appliance);
        return new ResponseEntity<>(createdAppliance, HttpStatus.CREATED);
    }

    // Endpoint to update an existing appliance record
    @PutMapping("/{id}")
    public ResponseEntity<Appliance> updateAppliance(@PathVariable Long id, @RequestBody Appliance appliance) {
        Appliance updatedAppliance = applianceService.updateAppliance(id, appliance);
        if (updatedAppliance != null) {
            return new ResponseEntity<>(updatedAppliance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an appliance record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppliance(@PathVariable Long id) {
        boolean deleted = applianceService.deleteAppliance(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
