package com.mytodo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mytodo.dto.LabelDTO;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class LabelServiceTest {
	
	@MockitoBean
	private ILabelRepository repository;
	
	@Autowired
	private LabelService service;

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
	}

}
