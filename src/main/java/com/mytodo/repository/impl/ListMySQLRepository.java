package com.mytodo.repository.impl;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import com.mytodo.common.Constants;
import com.mytodo.entity.LabelEntity;
import com.mytodo.entity.ListEntity;
import com.mytodo.entity.TaskEntity;
import com.mytodo.repository.IListRepository;
import com.mytodo.repository.infra.MySQLDataSource;

import static org.jooq.SQLDialect.MYSQL;

import java.util.ArrayList;
import java.util.List;

@Component(Constants.LIST_REPOSITORY)
public class ListMySQLRepository implements IListRepository {

	@Override
	public ListEntity getListById(String list) {
		MySQLDataSource data = MySQLDataSource.getInstance();
		
		DSLContext create = DSL.using(data.getConnection(), MYSQL);
		
		List<Record> results = create
			.select()
			.from("list_view")
			.where(DSL.field("list_id").eq(list))
			.fetch();
		
		Record record1 = results.get(0);
		
		ListEntity entity = new ListEntity();
		
		entity.setId(list);
		entity.setTitle(record1.getValue("list_title", String.class));
		entity.setDescription(record1.getValue("list_description", String.class));
		
		List<TaskEntity> tasks = new ArrayList<TaskEntity>();

		for (Record rec : results) {			
			TaskEntity task = new TaskEntity();

			task.setId(rec.getValue("task_id", String.class));
			task.setTitle(rec.getValue("task_title", String.class));
			task.setDescription(rec.getValue("task_description", String.class));
			task.setStatus(rec.getValue("task_status", String.class));
			
			if (rec.getValue("labels", String.class) != null && (rec.getValue("label_ids", String.class) != null)) {
				List<LabelEntity> labels = new ArrayList<LabelEntity>();
				
				String[] labelNames = rec.getValue("labels", String.class).split(",");
				String[] labelIds = rec.getValue("label_ids", String.class).split(",");
				
				for (int i = 0; i < labelNames.length; i++) {
					LabelEntity label = new LabelEntity();
					
					label.setId(labelIds[i].trim());
					label.setName(labelNames[i].trim());
					
					labels.add(label);
				}
				
				task.setLabels(labels.toArray(LabelEntity[]::new));
			}
			
			tasks.add(task);
		}
		
		entity.setTasks(tasks.toArray(TaskEntity[]::new));
		
		return entity;
	}

}
