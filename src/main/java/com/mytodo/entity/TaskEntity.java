package com.mytodo.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskEntity {
	private String id;
	private String listId;
	private String name;
	private String description;
	private String categoryId;
	private boolean isCompleted;
	private Date created;
	private Date updated;
}
