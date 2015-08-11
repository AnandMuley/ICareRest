package abm.icare.controllers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.config.AbstractResourceBaseConfig;

public class VisitResourceTest extends AbstractResourceBaseConfig{

	private VisitResource visitResource;
	
	@BeforeClass
	public void initData(){
		visitResource = new VisitResource();
		super.setUpData(visitResource);
	}
	
	@Test
	public void shouldCreateVisit(){
	}

}
