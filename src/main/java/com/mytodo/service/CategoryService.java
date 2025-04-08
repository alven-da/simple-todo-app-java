package com.mytodo.service;

import java.util.ArrayList;
import java.util.List;

import com.mytodo.dto.CategoryDTO;
import com.mytodo.entity.CategoryEntity;
import com.mytodo.repository.ICategoryRepository;

public class CategoryService {
	private ICategoryRepository catRepo;
	
	private CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		
		return dto;
	}
	
	public CategoryService(ICategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public List<CategoryDTO> getAllCategories() {
		List<CategoryEntity> entities = this.catRepo.getCategories();
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		
		
		for (CategoryEntity obj : entities) {
			dtos.add(toDTO(obj));
		}
		
		return dtos;
	}
}
