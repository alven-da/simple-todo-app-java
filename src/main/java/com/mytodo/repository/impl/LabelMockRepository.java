package com.mytodo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;

// @Component(Constants.LABEL_REPOSITORY)
public class LabelMockRepository implements ILabelRepository {

	@Override
	public List<LabelEntity> getAll() {
		LabelEntity ent1 = new LabelEntity();
		LabelEntity ent2 = new LabelEntity();
		
		List<LabelEntity> respon = new ArrayList<LabelEntity>();
		
		ent1.setId("1");
		ent1.setName("Work-related");
		ent1.setDescription("This is a label for work-related tasks");
		
		ent2.setId("2");
		ent2.setName("Grocery");
		ent2.setDescription("This is a label for groceries");
		
		respon.add(ent1);
		respon.add(ent2);
		
		return respon;
	}

	@Override
	public boolean addSingleEntity(LabelEntity entity) {
		return true;
	}

}
