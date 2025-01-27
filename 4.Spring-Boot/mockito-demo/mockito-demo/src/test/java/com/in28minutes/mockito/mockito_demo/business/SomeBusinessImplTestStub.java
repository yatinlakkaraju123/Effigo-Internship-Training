package com.in28minutes.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplTestStub {

	@Test
	void findGreatestFromAllData_BasicScenario() {
		DataServiceStub dataServiceStub = new DataServiceStub();
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = someBusinessImpl.findGreatestFromAllData();
		assertEquals(result, 25);
	}

}

class DataServiceStub implements DataService{

	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[]{25,15,5};
	}
	
}
