package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.UserDTO;
import com.pocsma.jparelationalmodel.app.model.User;

@Mapper
public interface UserMapper {

	@Mapping(target = "id", source = "userId")
	UserDTO toDto(User user);
}
