package abm.icare.controllers;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;

import abm.icare.beans.Medicine;
import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.repositories.MedicineRepository;

public class SampleDataSetupResourceTest extends AbstractResourceBaseConfig {

	private SampleDataSetupResource sampleDataSetupResource;

	private MedicineRepository mockMedicineRepository;

	@BeforeClass
	public void setUpConfig() {
		sampleDataSetupResource = new SampleDataSetupResource();
		mockMedicineRepository = context.mock(MedicineRepository.class);
		ReflectionTestUtils.setField(sampleDataSetupResource,
				"medicineRepository", mockMedicineRepository);
		super.setUpData(sampleDataSetupResource);
	}

	@Test
	public void shouldSetUpSampleMedicinesData() {
		// GIVEN

		context.checking(new Expectations() {
			{
				allowing(mockMedicineRepository)
						.save(with(any(Medicine.class)));
			}
		});
		// WHEN
		ClientResponse response = resource().path(SETUP_MEDICINES)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(response.getEntity(String.class).toString(), "Medicines Data Setup Successfully !");
	}

}
