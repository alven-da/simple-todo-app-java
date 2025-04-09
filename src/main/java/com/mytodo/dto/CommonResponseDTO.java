package com.mytodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDTO {
	private String message;
	private int code;
	private String status;
}
