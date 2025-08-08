package com.mytodo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.mytodo.dto.LabelDTO;
import com.mytodo.service.LabelService;

class LabelControllerTest {

	@Mock
	private LabelService service;
	
	@InjectMocks
	private LabelController controller;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testShouldGetAllLabels_Success() {
		List<LabelDTO> dtos = new ArrayList<LabelDTO>();
		
		for (int i = 1; i <= 5; i++) {
			LabelDTO labelDTO = new LabelDTO();
			labelDTO.setId("MyLabel-" + i);
			labelDTO.setName("Cooking-" + i);
			
			dtos.add(labelDTO);
		}
		
		
		when(service.getLabels()).thenReturn(dtos);
		
		ResponseEntity<JsonNode> resp = controller.getLabels();
		
		assertNotNull(resp);
		assertTrue(resp.hasBody());
	}

}
