package com.mytodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.mytodo.dto.CommonResponseDTO;
import com.mytodo.service.CategoryService;

@RestController
@RequestMapping("/")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	private ObjectMapper mapper = TodoUtils.getObjectMapper();
	
	@GetMapping(value = "/categories", produces = "application/json")
	public ResponseEntity<JsonNode> getAllCategories() {
		return ResponseEntity.ok(this.mapper.convertValue(categoryService.getAllCategories(), JsonNode.class));
	}
	
	@PostMapping(value = "/category", produces = "application/json")
	public ResponseEntity<JsonNode> createCategory(@RequestBody String body) {
		CommonResponseDTO cer;
		JsonNode res;

		try {
			// ObjectMapper - throws Exception if does not match the class
			CategoryDTO dto = this.mapper.readValue(body, CategoryDTO.class);
			
			this.categoryService.addCategory(dto);
			
			cer = TodoUtils.createCommonResponse(
					HttpStatus.OK.name(),
					HttpStatus.OK.value(),
					"Category has been added successfully"
				);
			
			return ResponseEntity.ok(mapper.convertValue(cer, JsonNode.class));
		} catch (JsonMappingException e) {
			cer = TodoUtils.createCommonResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), e.getOriginalMessage());
			res = this.mapper.convertValue(cer, JsonNode.class);
			
			e.printStackTrace();
			
			return new ResponseEntity<JsonNode>(res, HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			cer = TodoUtils.createCommonResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), e.getOriginalMessage());
			res = this.mapper.convertValue(cer, JsonNode.class);
			
			e.printStackTrace();
			
			return new ResponseEntity<JsonNode>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
