package com.mytodo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mytodo.common.Constants;
import com.mytodo.dto.CategoryDTO;
import com.mytodo.entity.CategoryEntity;
import com.mytodo.repository.ICategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	@Qualifier(Constants.CATEGORY_REPOSITORY)
	private ICategoryRepository catRepo;
	
	private CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		
		return dto;
	}
	
	private CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}
	
	public List<CategoryDTO> getAllCategories() {
		List<CategoryEntity> entities = this.catRepo.getAll();
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		
		
		for (CategoryEntity obj : entities) {
			dtos.add(toDTO(obj));
		}
		
		return dtos;
	}
	
	public boolean addCategory(CategoryDTO dto) {		
		try {
			return this.catRepo.addSingleEntity(this.toEntity(dto));
		} catch (Exception ex) {
			return false;
		}
	}
}
