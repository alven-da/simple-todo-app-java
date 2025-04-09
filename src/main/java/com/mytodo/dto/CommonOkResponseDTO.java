package com.mytodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonOkResponseDTO<T> {
	private T data;
	private String message;
}
