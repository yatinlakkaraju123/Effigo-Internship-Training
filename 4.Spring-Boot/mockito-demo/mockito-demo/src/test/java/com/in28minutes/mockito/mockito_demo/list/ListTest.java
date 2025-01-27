package com.in28minutes.mockito.mockito_demo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {
	
	@Test
	public void test() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);
		
		assertEquals(3,listMock.size());
	}
	
	
	@Test
	public void multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3).thenReturn(2);
		
		assertEquals(3,listMock.size());
		assertEquals(2,listMock.size());
	}

}
