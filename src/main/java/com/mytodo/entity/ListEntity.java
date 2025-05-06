package com.mytodo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListEntity {
	private String id;
	private String title;
	private String description;
	private TaskEntity[] tasks;
}
