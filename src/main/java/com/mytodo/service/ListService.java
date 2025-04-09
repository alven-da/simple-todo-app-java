package com.mytodo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mytodo.common.Constants;
import com.mytodo.dto.ListDTO;
import com.mytodo.dto.TaskDTO;
import com.mytodo.entity.ListEntity;
import com.mytodo.entity.TaskEntity;
import com.mytodo.repository.IListRepository;
import com.mytodo.repository.ITaskRepository;

@Service
public class ListService {
	
	@Autowired
	@Qualifier(Constants.LIST_REPOSITORY)
	private IListRepository listRepository;
	
	@Autowired
	@Qualifier(Constants.TASK_REPOSITORY)
	private ITaskRepository taskRepository;
	
	private ListDTO toDTO(ListEntity listEntity, TaskEntity[] taskEntities) {
		ListDTO dto = new ListDTO();
		
		dto.setListId(listEntity.getId());
		
		List<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
		
		for (TaskEntity t : taskEntities) {
			TaskDTO taskDto = new TaskDTO();
			
			taskDto.setTaskId(t.getId());
			taskDto.setName(t.getName());
			taskDto.setCategory(t.getCategoryId());
			taskDto.setCompleted(t.isCompleted());
			
			taskDtos.add(taskDto);
		}
		
		dto.setTasks(taskDtos.toArray(TaskDTO[]::new));
		
		return dto;
	}
	
	public ListDTO getListByOwner(String ownerId) {
		ListEntity list = this.listRepository.getListByOwner(ownerId);
		TaskEntity[] tasks = this.taskRepository.getTasksByListId(list.getId());

		return this.toDTO(list, tasks);
	}
}
