package com.in28minutes.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplTestMockito {
	@Mock
	private DataService dataService;
	@InjectMocks
	private SomeBusinessImpl someBusinessImpl;
	@Test
	void findGreatestFromAllData_BasicScenario() {
		//DataService mock2 = mock(DataService.class);
		//DataServiceStub dataServiceStub = new DataServiceStub(mock2);
		when(dataService.retrieveAllData()).thenReturn(new int[] {10,25,15});
		//SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(mock2);
		int result = someBusinessImpl.findGreatestFromAllData();
		assertEquals(result, 25);
	}

}


