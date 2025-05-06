package com.mytodo.repository;

import java.util.List;

import com.mytodo.entity.LabelEntity;

public interface ILabelRepository {
	public List<LabelEntity> getAll();
	public boolean addSingleEntity(LabelEntity entity);
}
