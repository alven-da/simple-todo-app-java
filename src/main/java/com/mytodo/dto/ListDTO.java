package com.mytodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDTO {
	private String listId;
	private TaskDTO[] tasks;
}
