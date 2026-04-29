package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.StoreDTO;
import com.pocsma.jparelationalmodel.app.model.Store;

@Mapper(uses = CityMapper.class)
public interface StoreMapper {

	@Mapping(target = "id", source = "storeId")
	StoreDTO toDto(Store store);
}
