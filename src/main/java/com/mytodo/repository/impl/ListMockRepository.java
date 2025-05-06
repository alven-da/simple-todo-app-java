package com.mytodo.repository.impl;

import com.mytodo.entity.ListEntity;
import com.mytodo.repository.IListRepository;


public class ListMockRepository implements IListRepository {

	@Override
	public ListEntity getListById(String ownerId) {
		ListEntity entity = new ListEntity();
		
		entity.setId("1");
		entity.setTitle("Test Title");
		
		return entity;
	}

}
