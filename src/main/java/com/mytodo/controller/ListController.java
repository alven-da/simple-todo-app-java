package com.mytodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodo.common.TodoUtils;
import com.mytodo.dto.CommonOkResponseDTO;
import com.mytodo.dto.ListDTO;
import com.mytodo.service.ListService;

@RestController
@RequestMapping(value = "/list", produces = "application/json")
public class ListController {

	@Autowired
	private ListService listService;
	
	private ObjectMapper mapper = TodoUtils.getObjectMapper();
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<JsonNode> getListById(@PathVariable("id") String id) {
		ListDTO dto = this.listService.getListByOwner(id);
		
		CommonOkResponseDTO<ListDTO> resp = new CommonOkResponseDTO<ListDTO>();
		resp.setData(dto);
		
		return new ResponseEntity<JsonNode>(this.mapper.convertValue(resp, JsonNode.class), HttpStatus.OK);
	}
}
