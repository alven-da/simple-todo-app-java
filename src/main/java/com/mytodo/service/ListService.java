package com.mytodo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mytodo.common.Constants;
import com.mytodo.dto.LabelDTO;
import com.mytodo.dto.ListDTO;
import com.mytodo.dto.TaskDTO;
import com.mytodo.entity.LabelEntity;
import com.mytodo.entity.ListEntity;
import com.mytodo.entity.TaskEntity;
import com.mytodo.repository.IListRepository;

@Service
public class ListService {
	
	@Autowired
	@Qualifier(Constants.LIST_REPOSITORY)
	private IListRepository listRepository;

	
	private ListDTO toDTO(ListEntity listEntity) {
		ListDTO dto = new ListDTO();
		
		dto.setListId(listEntity.getId());
		dto.setTitle(listEntity.getTitle());
		
		List<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
		
		if (listEntity.getTasks() != null) {
			for (TaskEntity t : listEntity.getTasks()) {
				TaskDTO taskDto = new TaskDTO();
				
				taskDto.setTaskId(t.getId());
				taskDto.setName(t.getTitle());
				taskDto.setStatus(t.getStatus());
				
				List<LabelDTO> labelDtos = new ArrayList<LabelDTO>();
				
				if (t.getLabels() != null) {
					for (LabelEntity le : t.getLabels()) {
						LabelDTO labelDto = new LabelDTO();
						
						labelDto.setId(le.getId());
						labelDto.setName(le.getName());
						
						labelDtos.add(labelDto);
					}
				}
				
				taskDto.setLabels(labelDtos.toArray(LabelDTO[]::new));
				
				taskDtos.add(taskDto);
			}
			
			dto.setTasks(taskDtos.toArray(TaskDTO[]::new));
		}	
		
		return dto;
	}
	
	public ListDTO getListById(String listId) {
		ListEntity list = this.listRepository.getListById(listId);

		return this.toDTO(list);
	}
}
