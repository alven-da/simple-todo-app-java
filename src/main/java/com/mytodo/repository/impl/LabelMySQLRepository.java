package com.mytodo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;
import com.mytodo.repository.infra.MySQLDataSource;

import static org.jooq.SQLDialect.MYSQL;

//@Component(Constants.LABEL_REPOSITORY)
public class LabelMySQLRepository implements ILabelRepository {

	@Override
	public List<LabelEntity> getAll() {
		MySQLDataSource ds = MySQLDataSource.getInstance();
		DSLContext create = DSL.using(ds.getConnection(), MYSQL);
		
		List<Record2<String, String>> results = create.select(
			DSL.field("id", String.class),
			DSL.field("name", String.class)
			)
			.from("label")
			.fetch();
		
		List<LabelEntity> entities = new ArrayList<LabelEntity>();
		
		for (Record2<String, String> record : results) {
            String listId = (String) record.getValue("id");
            String listName = (String) record.getValue("name");
            
            LabelEntity entity = new LabelEntity();
            
            entity.setId(listId);
            entity.setName(listName);

            entities.add(entity);
        }
		
		return entities;
	}

	@Override
	public boolean addSingleEntity(LabelEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
