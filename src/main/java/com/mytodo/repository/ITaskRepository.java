package com.mytodo.repository;

import com.mytodo.entity.TaskEntity;

public interface ITaskRepository {
	public TaskEntity[] getTasksByListId(String listId);
}
