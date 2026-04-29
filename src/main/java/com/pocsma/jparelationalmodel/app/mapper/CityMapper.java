package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.CityDTO;
import com.pocsma.jparelationalmodel.app.model.City;

@Mapper(uses = CountryMapper.class)
public interface CityMapper {

	@Mapping(target = "id", source = "cityId")
	CityDTO toDto(City city);
}
