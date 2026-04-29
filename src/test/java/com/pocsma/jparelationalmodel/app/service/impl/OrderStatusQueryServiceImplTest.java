package com.pocsma.jparelationalmodel.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.model.OrderStatus;
import com.pocsma.jparelationalmodel.app.repository.OrderStatusRepository;

@ExtendWith(MockitoExtension.class)
class OrderStatusQueryServiceImplTest {

	@Mock
	private OrderStatusRepository orderStatusRepository;

	@Mock
	private OrderStatusMapper orderStatusMapper;

	@InjectMocks
	private OrderStatusQueryServiceImpl orderStatusQueryService;

	@Test
	void shouldReturnMappedOrderStatuses() {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderStatusId("order-status-1");

		OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
				"order-status-1",
				LocalDateTime.of(2026, 4, 29, 10, 0),
				null);

		when(orderStatusRepository.findAll()).thenReturn(List.of(orderStatus));
		when(orderStatusMapper.toDto(orderStatus)).thenReturn(orderStatusDTO);

		List<OrderStatusDTO> result = orderStatusQueryService.findAll();

		assertThat(result).containsExactly(orderStatusDTO);
		verify(orderStatusRepository).findAll();
		verify(orderStatusMapper).toDto(orderStatus);
	}
}
