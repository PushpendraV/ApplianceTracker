package com.company.MyApplianceTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.company.appliancemanagement.model.Appliance;

public class ApplianceTest {

	@Test
	public void testConstructorWithArguments() {
		String serialNumber = "SN123456";
		String brand = "ExampleBrand";
		String model = "ExampleModel";
		Date dateBought = new Date();
		String status = "Active";

		Appliance appliance = new Appliance(serialNumber, brand, model, dateBought, status);

		assertNotNull(appliance);
		assertEquals(serialNumber, appliance.getSerialNumber());
		assertEquals(brand, appliance.getBrand());
		assertEquals(model, appliance.getModel());
		assertEquals(dateBought, appliance.getDateBought());
		assertEquals(status, appliance.getStatus());
	}
	@Test
    public void testGettersAndSetters() {
        Appliance appliance = new Appliance();

        Long id = 1L;
        String serialNumber = "SN123456";
        String brand = "ExampleBrand";
        String model = "ExampleModel";
        Date dateBought = new Date();
        String status = "Active";

        appliance.setId(id);
        appliance.setSerialNumber(serialNumber);
        appliance.setBrand(brand);
        appliance.setModel(model);
        appliance.setDateBought(dateBought);
        appliance.setStatus(status);

        assertEquals(id, appliance.getId());
        assertEquals(serialNumber, appliance.getSerialNumber());
        assertEquals(brand, appliance.getBrand());
        assertEquals(model, appliance.getModel());
        assertEquals(dateBought, appliance.getDateBought());
        assertEquals(status, appliance.getStatus());
    }
}
