package com.mytodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
	private String taskId;
	private String name;
	private String category;
	private boolean isCompleted;
}
