package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.constants.AppConstants;
import abm.icare.dataproviders.MedicineDataProvider;
import abm.icare.dtos.MedicineDto;
import abm.icare.exceptions.MedicineNotFoundException;
import abm.icare.exceptions.MedicineServiceException;
import abm.icare.services.MedicineService;

public class MedicineResourceTest extends AbstractResourceBaseConfig {

	private MedicineResource medicineResource;

	private MedicineService mockMedicineService;

	@BeforeClass
	public void setData() {
		mockMedicineService = context.mock(MedicineService.class);
		medicineResource = new MedicineResource();
		ReflectionTestUtils.setField(medicineResource, "medicineService",
				mockMedicineService);
		super.setUpData(medicineResource);
	}

	@Test
	public void shouldSearchMedicineByName() throws MedicineServiceException {
		// GIVEN
		final String name = "croc";
		final List<MedicineDto> medicineDtos = MedicineDataProvider
				.getMedicineDtos();

		context.checking(new Expectations() {
			{
				oneOf(mockMedicineService).fetchBy(with(name));
				will(returnValue(medicineDtos));
			}
		});
		// WHEN
		WebResource resource = resource().path(MEDICINE_SEARCH_BY_NAME)
				.queryParam("name", name);
		ClientResponse clientResponse = resource.get(ClientResponse.class);
		List<MedicineDto> medicines = clientResponse
				.getEntity(new GenericType<List<MedicineDto>>() {
				});

		// THEN
		Assert.assertEquals(clientResponse.getStatus(), 200);
		Assert.assertEquals(medicines.size(), 3);
	}

	@Test
	public void shouldReturnNotFoundWhenMedicineNotFound()
			throws MedicineServiceException {
		// GIVEN
		final String name = "Dracula";

		context.checking(new Expectations() {
			{
				oneOf(mockMedicineService).fetchBy(with(name));
				will(throwException(new MedicineNotFoundException(
						AppConstants.EXCEPTION_MEDICINE_NOT_FOUND)));
			}
		});
		// WHEN
		WebResource webResource = resource().path(MEDICINE_SEARCH_BY_NAME)
				.queryParam("name", name);
		ClientResponse clientResponse = webResource.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void shouldReturnInternalServerError()
			throws MedicineServiceException {
		// GIVEN
		final String name = "@%#$#%#";

		context.checking(new Expectations() {
			{
				oneOf(mockMedicineService).fetchBy(with(name));
				will(throwException(new MedicineServiceException(
						"Some other exception")));
			}
		});
		// WHEN
		WebResource webResource = resource().path(MEDICINE_SEARCH_BY_NAME)
				.queryParam("name", name);
		ClientResponse clientResponse = webResource.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}

}
