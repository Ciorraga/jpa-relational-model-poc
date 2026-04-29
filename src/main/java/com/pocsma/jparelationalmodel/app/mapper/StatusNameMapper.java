package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.StatusNameDTO;
import com.pocsma.jparelationalmodel.app.model.StatusName;

@Mapper
public interface StatusNameMapper {

	@Mapping(target = "id", source = "statusNameId")
	StatusNameDTO toDto(StatusName statusName);
}
