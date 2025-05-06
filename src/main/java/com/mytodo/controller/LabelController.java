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
import com.mytodo.dto.LabelDTO;
import com.mytodo.dto.CommonResponseDTO;
import com.mytodo.service.LabelService;

@RestController
@RequestMapping("/")
public class LabelController {
	
	@Autowired
	private LabelService labelService;
	
	private ObjectMapper mapper = TodoUtils.getObjectMapper();
	
	@GetMapping(value = "/labels", produces = "application/json")
	public ResponseEntity<JsonNode> getLabels() {
		return ResponseEntity.ok(this.mapper.convertValue(labelService.getLabels(), JsonNode.class));
	}
	
	@PostMapping(value = "/label", produces = "application/json")
	public ResponseEntity<JsonNode> createLabel(@RequestBody String body) {
		CommonResponseDTO cer;
		JsonNode res;

		try {
			// ObjectMapper - throws Exception if does not match the class
			LabelDTO dto = this.mapper.readValue(body, LabelDTO.class);
			
			this.labelService.addLabel(dto);
			
			cer = TodoUtils.createCommonResponse(
					HttpStatus.OK.name(),
					HttpStatus.OK.value(),
					"Label has been added successfully"
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
