package com.mytodo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;
import com.mytodo.repository.infra.PostgreSQLDataSource;

import static org.jooq.SQLDialect.POSTGRES;

@Component(Constants.LABEL_REPOSITORY)
public class LabelPostgreSQLRepository implements ILabelRepository {

	@Override
	public List<LabelEntity> getAll() {
		PostgreSQLDataSource ds = PostgreSQLDataSource.getInstance();
		DSLContext create = DSL.using(ds.getConnection(), POSTGRES);
		
		List<Record> results = create.select()
				.from("label")
				.fetch();
		
		List<LabelEntity> labels = new ArrayList<LabelEntity>();
		
		for (Record rec : results) {
			LabelEntity label = new LabelEntity();
			
			label.setId(rec.getValue("id", String.class));
			label.setName(rec.getValue("name", String.class));
			
			labels.add(label);
		}

		return labels;
	}

	@Override
	public boolean addSingleEntity(LabelEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
