package com.company.appliancemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.appliancemanagement.model.Appliance;
import com.company.appliancemanagement.repository.ApplianceRepository;

@Service
public class ApplianceService {

	private final ApplianceRepository applianceRepository;

	@Autowired
	public ApplianceService(ApplianceRepository applianceRepository) {
		this.applianceRepository = applianceRepository;
	}

	// Get all appliances
	public List<Appliance> getAllAppliances() {
		return applianceRepository.findAll();
	}

	// Get a specific appliance by ID
	public Appliance getApplianceById(Long id) {
		Optional<Appliance> appliance = applianceRepository.findById(id);
		return appliance.orElse(null);
	}

	// Create a new appliance
	public Appliance createAppliance(Appliance appliance) {
		return applianceRepository.save(appliance);
	}

	// Update an existing appliance
	public Appliance updateAppliance(Long id, Appliance updatedAppliance) {
		Optional<Appliance> optionalAppliance = applianceRepository.findById(id);
		if (optionalAppliance.isPresent()) {
			Appliance existingAppliance = optionalAppliance.get();
			// Update the properties of the existing appliance
			existingAppliance.setSerialNumber(updatedAppliance.getSerialNumber());
			existingAppliance.setBrand(updatedAppliance.getBrand());
			existingAppliance.setModel(updatedAppliance.getModel());
			existingAppliance.setDateBought(updatedAppliance.getDateBought());
			existingAppliance.setStatus(updatedAppliance.getStatus());
			return applianceRepository.save(existingAppliance);
		} else {
			return null; // Appliance not found
		}
	}

	// Delete an appliance
	public boolean deleteAppliance(Long id) {
		Optional<Appliance> optionalAppliance = applianceRepository.findById(id);
		if (optionalAppliance.isPresent()) {
			applianceRepository.deleteById(id);
			return true; // Appliance deleted successfully
		} else {
			return false; // Appliance not found
		}
	}
}
