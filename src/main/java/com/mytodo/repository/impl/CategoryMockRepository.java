package com.mytodo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.mytodo.entity.CategoryEntity;
import com.mytodo.repository.ICategoryRepository;

public class CategoryMockRepository implements ICategoryRepository {

	@Override
	public List<CategoryEntity> getCategories() {
		// TODO Auto-generated method stub
		CategoryEntity ent1 = new CategoryEntity();
		CategoryEntity ent2 = new CategoryEntity();
		
		List<CategoryEntity> respon = new ArrayList<CategoryEntity>();
		
		ent1.setId("1");
		ent1.setName("Work-related");
		ent1.setDescription("This is a category for work-related tasks");
		
		ent2.setId("2");
		ent2.setName("Grocery");
		ent2.setDescription("This is a category for groceries");
		
		respon.add(ent1);
		respon.add(ent2);
		
		return respon;
	}

}
