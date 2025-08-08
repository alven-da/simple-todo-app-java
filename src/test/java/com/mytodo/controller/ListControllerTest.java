package com.mytodo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.mytodo.dto.ListDTO;
import com.mytodo.dto.TaskDTO;
import com.mytodo.service.ListService;

class ListControllerTest {

	@Mock
	private ListService service;

	@InjectMocks
	private ListController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testShouldGetList_Success() {
		ListDTO dto = new ListDTO();
		String id = "myId";

		List<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
		TaskDTO taskDto = new TaskDTO();
		
		taskDto.setTaskId("task-1");
		taskDto.setName("Create a unit test");
		taskDto.setStatus("PENDING");

		taskDtos.add(taskDto);

		dto.setTitle("title");
		dto.setListId(id);
		dto.setTasks(taskDtos.toArray(TaskDTO[]::new));

		when(service.getListById(id)).thenReturn(dto);

		ResponseEntity<JsonNode> resp = controller.getListById(id);

		assertNotNull(resp);

		JsonNode body = resp.getBody();
		
		assertEquals(body.get("data").get("title").asText(), "title");
		assertEquals(body.get("data").get("tasks").size(), 1);

	}

}
