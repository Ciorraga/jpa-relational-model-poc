package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.ProductDTO;
import com.pocsma.jparelationalmodel.app.model.Product;

@Mapper
public interface ProductMapper {

	@Mapping(target = "id", source = "productId")
	ProductDTO toDto(Product product);
}
