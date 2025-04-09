package com.mytodo.repository;

import java.util.List;

import com.mytodo.entity.CategoryEntity;

public interface ICategoryRepository {
	public List<CategoryEntity> getAll();
	public boolean addSingleEntity(CategoryEntity entity);
}
