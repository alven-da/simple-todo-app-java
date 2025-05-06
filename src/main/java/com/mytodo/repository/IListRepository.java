package com.mytodo.repository;

import com.mytodo.entity.ListEntity;

public interface IListRepository {
	public ListEntity getListById(String ownerId);
}
