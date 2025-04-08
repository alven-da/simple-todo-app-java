package com.mytodo.common;

import com.mytodo.dto.CommonErrorResponseDTO;

public class TodoUtils {

	public static CommonErrorResponseDTO createCommonResponse(String status, int code, String message) {
		CommonErrorResponseDTO dto = new CommonErrorResponseDTO();
		
		dto.setCode(code);
		dto.setMessage(message);
		dto.setStatus(status);
		
		return dto;
	}
}
