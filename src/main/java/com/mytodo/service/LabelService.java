package com.mytodo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mytodo.common.Constants;
import com.mytodo.dto.LabelDTO;
import com.mytodo.entity.LabelEntity;
import com.mytodo.repository.ILabelRepository;

@Service
public class LabelService {

	@Autowired
	@Qualifier(Constants.LABEL_REPOSITORY)
	private ILabelRepository labelRepo;

	private LabelDTO toDTO(LabelEntity entity) {
		LabelDTO dto = new LabelDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());

		return dto;
	}

	private LabelEntity toEntity(LabelDTO dto) {
		LabelEntity entity = new LabelEntity();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());

		return entity;
	}

	public List<LabelDTO> getLabels() {
		List<LabelEntity> entities = labelRepo.getAll();

		List<LabelDTO> dtos = new ArrayList<LabelDTO>();

		for (LabelEntity obj : entities) {
			dtos.add(toDTO(obj));
		}

		return dtos;
	}

	public boolean addLabel(LabelDTO dto) {
		try {
			return labelRepo.addSingleEntity(toEntity(dto));
		} catch (Exception ex) {
			return false;
		}
	}
}
