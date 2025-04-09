package com.mytodo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodo.dto.CommonResponseDTO;
import com.mytodo.dto.CommonOkResponseDTO;

public class TodoUtils {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static CommonResponseDTO createCommonResponse(String status, int code, String message) {
		CommonResponseDTO dto = new CommonResponseDTO();
		
		dto.setCode(code);
		dto.setMessage(message);
		dto.setStatus(status);
		
		return dto;
	}
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
