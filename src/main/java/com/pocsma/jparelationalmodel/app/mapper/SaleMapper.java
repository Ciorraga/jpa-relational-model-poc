package com.pocsma.jparelationalmodel.app.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.SaleDTO;
import com.pocsma.jparelationalmodel.app.model.Sale;

@Mapper(uses = {ProductMapper.class, StoreMapper.class, UserMapper.class})
public interface SaleMapper {

	@Mapping(target = "id", source = "saleId")
	@Mapping(target = "orderStatuses", expression = "java(orderStatusMapper.toSortedDtoList(sale.getOrderStatuses()))")
	SaleDTO toDto(Sale sale, @Context OrderStatusMapper orderStatusMapper);
}
