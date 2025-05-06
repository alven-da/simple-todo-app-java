package com.mytodo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskEntity {
	private String id;
	private String listId;
	private String title;
	private String description;
	private String status;
	private LabelEntity[] labels;
}
