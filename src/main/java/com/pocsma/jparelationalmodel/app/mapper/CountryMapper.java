package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.CountryDTO;
import com.pocsma.jparelationalmodel.app.model.Country;

@Mapper
public interface CountryMapper {

	@Mapping(target = "id", source = "countryId")
	CountryDTO toDto(Country country);
}
