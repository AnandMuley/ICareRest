package abm.icare.controllers;

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
import abm.icare.services.VisitService;

import com.sun.jersey.api.client.ClientResponse;

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

}
