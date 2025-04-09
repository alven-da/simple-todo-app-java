package com.mytodo.repository.impl;

import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.ListEntity;
import com.mytodo.repository.IListRepository;

@Component(Constants.LIST_REPOSITORY)
public class ListMockRepository implements IListRepository {

	@Override
	public ListEntity getListByOwner(String ownerId) {
		ListEntity entity = new ListEntity();
		
		entity.setId("1");
		entity.setOwner(ownerId);
		
		return entity;
	}

}
