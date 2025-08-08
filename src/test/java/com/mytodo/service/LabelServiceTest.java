package com.mytodo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.mytodo.dto.LabelDTO;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class LabelServiceTest {
	
	@Mock
	private ILabelRepository repository;
	
	@InjectMocks
	private LabelService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testShouldListAllLabels() {

		List<LabelEntity> labelEntities = new ArrayList<LabelEntity>();
		
		for (int i = 1; i <= 5; i++) {
			LabelEntity labelEntity = new LabelEntity();
			labelEntity.setId("MyLabel-" + i);
			labelEntity.setName("Cooking-" + i);
			
			labelEntities.add(labelEntity);
		}
		
		when(repository.getAll()).thenReturn(labelEntities);
		
		List<LabelDTO> labelDtos = service.getLabels();
		
		assertEquals(labelDtos.get(0).getId(), "MyLabel-1");
		assertEquals(labelDtos.get(0).getName(), "Cooking-1");

	}
	
	@Test
	void testShouldAddLabel_Success() {
		LabelDTO labelDto = new LabelDTO();
		labelDto.setId("MyLabel-1");
		labelDto.setName("Cooking-1");
		
		LabelEntity labelEntity = new LabelEntity();
		labelEntity.setId(labelDto.getId());
		labelEntity.setName(labelDto.getName());
	
		when(repository.addSingleEntity(any(LabelEntity.class))).thenReturn(true);
		
		boolean isSuccess = service.addLabel(labelDto);
		
		assertTrue(isSuccess);
	}

}
