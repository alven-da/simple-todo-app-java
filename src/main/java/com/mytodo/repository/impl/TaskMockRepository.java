package com.mytodo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.TaskEntity;
import com.mytodo.repository.ITaskRepository;

@Component(Constants.TASK_REPOSITORY)
public class TaskMockRepository implements ITaskRepository {

	@Override
	public TaskEntity[] getTasksByListId(String listId) {
		List<TaskEntity> entities = new ArrayList<TaskEntity>();
		
		TaskEntity ent1 = new TaskEntity();
		
		ent1.setId("1");
		ent1.setListId(listId);
		ent1.setName("Go to market");
		ent1.setCategoryId("3");
		ent1.setCompleted(false);
		
		entities.add(ent1);
		
		TaskEntity ent2 = new TaskEntity();

		ent2.setId("2");
		ent2.setListId(listId);
		ent2.setName("Study Spring Boot");
		ent2.setCategoryId("2");
		ent2.setCompleted(true);
		
		entities.add(ent2);
		
		return entities.toArray(TaskEntity[]::new);
	}

}
