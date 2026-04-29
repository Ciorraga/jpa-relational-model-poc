package com.pocsma.jparelationalmodel.app.mapper;

import java.util.Comparator;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.model.OrderStatus;

@Mapper(uses = StatusNameMapper.class)
public interface OrderStatusMapper {

	@Mapping(target = "id", source = "orderStatusId")
	OrderStatusDTO toDto(OrderStatus orderStatus);

	default List<OrderStatusDTO> toSortedDtoList(List<OrderStatus> orderStatuses) {
		if (orderStatuses == null || orderStatuses.isEmpty()) {
			return List.of();
		}

		return orderStatuses.stream()
				.sorted(Comparator.comparing(OrderStatus::getUpdatedAt, Comparator.nullsLast(Comparator.naturalOrder())))
				.map(this::toDto)
				.toList();
	}
}
