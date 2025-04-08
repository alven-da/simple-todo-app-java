package com.mytodo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodo.common.TodoUtils;
import com.mytodo.dto.CategoryDTO;
import com.mytodo.dto.CommonErrorResponseDTO;
import com.mytodo.repository.ICategoryRepository;
import com.mytodo.repository.impl.CategoryMockRepository;
import com.mytodo.service.CategoryService;

@RestController
@RequestMapping("/")
public class CategoryController {
	
	@GetMapping(value = "/categories", produces = "application/json")
	public ResponseEntity<JsonNode> getAllCategories() {
		ICategoryRepository repo = new CategoryMockRepository();
		
		CategoryService svc = new CategoryService(repo);	
		ObjectMapper mapper = new ObjectMapper();
		
		return ResponseEntity.ok(mapper.convertValue(svc.getAllCategories(), JsonNode.class));
	}
	
	@PostMapping(value = "/category", produces = "application/json")
	public ResponseEntity<JsonNode> createCategory(@RequestBody String body) {
		ObjectMapper mapper = new ObjectMapper();
		
		CommonErrorResponseDTO cer;
		JsonNode res;

		try {
			// ObjectMapper - throws Exception if does not match the class
			CategoryDTO dto = mapper.readValue(body, CategoryDTO.class);
			
			
			
		} catch (JsonMappingException e) {
			cer = TodoUtils.createCommonResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), e.getOriginalMessage());
			res = mapper.convertValue(cer, JsonNode.class);
			
			e.printStackTrace();
			
			return new ResponseEntity<JsonNode>(res, HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			cer = TodoUtils.createCommonResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), e.getOriginalMessage());
			res = mapper.convertValue(cer, JsonNode.class);
			
			e.printStackTrace();
			
			return new ResponseEntity<JsonNode>(res, HttpStatus.BAD_REQUEST);
		}
		
		cer = TodoUtils.createCommonResponse(
				HttpStatus.OK.name(),
				HttpStatus.OK.value(),
				"Category has been added successfully"
			);
		
		return ResponseEntity.ok(mapper.convertValue(cer, JsonNode.class));
	}
}
