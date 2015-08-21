package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.dataproviders.VisitDataProvider;
import abm.icare.dtos.VisitDto;
import abm.icare.exceptions.UpdateVisitException;
import abm.icare.exceptions.VisitServiceException;
import abm.icare.services.VisitService;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class VisitResourceTest extends AbstractResourceBaseConfig {

	private VisitResource visitResource;

	private VisitService mockVisitService;

	@BeforeClass
	public void initData() {
		visitResource = new VisitResource();
		mockVisitService = context.mock(VisitService.class);
		ReflectionTestUtils.setField(visitResource, "visitService",
				mockVisitService);
		super.setUpData(visitResource);
	}

	@Test
	public void shouldCreateVisit() {
		// GIVEN
		final VisitDto visitDto = VisitDataProvider.createVisitDto();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitService).create(with(visitDto));
			}
		});
		// WHEN
		ClientResponse clientResponse = resource().path(VISIT_CREATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, visitDto);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void shouldUpdateVisit() throws VisitServiceException {
		// GIVEN
		final VisitDto visitDto = VisitDataProvider.createVisitDto();
		visitDto.setId("VID101");

		context.checking(new Expectations() {
			{
				oneOf(mockVisitService).update(with(visitDto));
			}
		});
		// WHEN
		ClientResponse clientResponse = resource().path(VISIT_UPDATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, visitDto);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.OK.getStatusCode());
	}

	@Test
	public void shouldReturn400IfVisitIdIsNull() throws VisitServiceException {
		// GIVEN
		final VisitDto visitDto = VisitDataProvider.createVisitDto();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitService).update(with(visitDto));
				will(throwException(new UpdateVisitException(
						"VisitId Should not be Null")));
			}
		});

		// WHEN
		ClientResponse clientResponse = resource().path(VISIT_UPDATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, visitDto);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void shouldReturn400IfVisitIdIsEmpty() throws VisitServiceException {
		// GIVEN
		final VisitDto visitDto = VisitDataProvider.createVisitDto();
		visitDto.setId("");

		context.checking(new Expectations() {
			{
				oneOf(mockVisitService).update(with(visitDto));
				will(throwException(new UpdateVisitException(
						"VisitId Should not be Null")));
			}
		});

		// WHEN
		ClientResponse clientResponse = resource().path(VISIT_UPDATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, visitDto);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void shouldReturnAllVisits() throws VisitServiceException {
		// GIVEN
		final List<VisitDto> visitDtos = VisitDataProvider.createVisitDtos();
		final String patientId = "PID101";

		context.checking(new Expectations() {
			{
				oneOf(mockVisitService).findAllVisitsOf(with(patientId));
				will(returnValue(visitDtos));
			}
		});

		// WHEN
		ClientResponse clientResponse = resource().path(VISIT_FIND_ALL)
				.queryParam("pid", patientId).get(ClientResponse.class);
		List<VisitDto> actualVisits = clientResponse
				.getEntity(new GenericType<List<VisitDto>>() {
				});

		// THEN
		Assert.assertEquals(clientResponse.getStatus(),
				Response.Status.OK.getStatusCode());
		Assert.assertEquals(actualVisits.size(), 2);
	}
}
