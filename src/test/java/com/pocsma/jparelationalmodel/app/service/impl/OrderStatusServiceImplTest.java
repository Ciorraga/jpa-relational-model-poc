package com.pocsma.jparelationalmodel.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.dto.OrderStatusRequestDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.model.OrderStatus;
import com.pocsma.jparelationalmodel.app.model.Sale;
import com.pocsma.jparelationalmodel.app.model.StatusName;
import com.pocsma.jparelationalmodel.app.repository.OrderStatusRepository;
import com.pocsma.jparelationalmodel.app.repository.SaleRepository;
import com.pocsma.jparelationalmodel.app.repository.StatusNameRepository;

@ExtendWith(MockitoExtension.class)
class OrderStatusServiceImplTest {

	@Mock
	private OrderStatusRepository orderStatusRepository;

	@Mock
	private SaleRepository saleRepository;

	@Mock
	private StatusNameRepository statusNameRepository;

	@Mock
	private OrderStatusMapper orderStatusMapper;

	@InjectMocks
	private OrderStatusServiceImpl orderStatusService;

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

		List<OrderStatusDTO> result = orderStatusService.findAll();

		assertThat(result).containsExactly(orderStatusDTO);
		verify(orderStatusRepository).findAll();
		verify(orderStatusMapper).toDto(orderStatus);
	}

	@Test
	void shouldReturnOrderStatusById() {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderStatusId("order-status-1");

		OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
				"order-status-1",
				LocalDateTime.of(2026, 4, 29, 10, 0),
				null);

		when(orderStatusRepository.findById("order-status-1")).thenReturn(Optional.of(orderStatus));
		when(orderStatusMapper.toDto(orderStatus)).thenReturn(orderStatusDTO);

		OrderStatusDTO result = orderStatusService.findById("order-status-1");

		assertThat(result).isEqualTo(orderStatusDTO);
		verify(orderStatusRepository).findById("order-status-1");
		verify(orderStatusMapper).toDto(orderStatus);
	}

	@Test
	void shouldCreateOrderStatus() {
		OrderStatusRequestDTO requestDTO = new OrderStatusRequestDTO(
				"order-status-1",
				LocalDateTime.of(2026, 5, 5, 10, 0),
				"sale-1",
				1);
		OrderStatus orderStatus = new OrderStatus();
		Sale sale = new Sale();
		StatusName statusName = new StatusName();
		OrderStatusDTO expected = new OrderStatusDTO(requestDTO.id(), requestDTO.updatedAt(), null);

		when(orderStatusMapper.toEntity(requestDTO)).thenReturn(orderStatus);
		when(saleRepository.findById("sale-1")).thenReturn(Optional.of(sale));
		when(statusNameRepository.findById(1)).thenReturn(Optional.of(statusName));
		when(orderStatusRepository.save(orderStatus)).thenReturn(orderStatus);
		when(orderStatusMapper.toDto(orderStatus)).thenReturn(expected);

		OrderStatusDTO result = orderStatusService.create(requestDTO);

		assertThat(result).isEqualTo(expected);
		assertThat(orderStatus.getSale()).isSameAs(sale);
		assertThat(orderStatus.getStatusName()).isSameAs(statusName);
		verify(orderStatusMapper).toEntity(requestDTO);
		verify(orderStatusRepository).save(orderStatus);
	}

	@Test
	void shouldUpdateOrderStatus() {
		OrderStatusRequestDTO requestDTO = new OrderStatusRequestDTO(
				null,
				LocalDateTime.of(2026, 5, 5, 11, 0),
				"sale-2",
				2);
		OrderStatus orderStatus = new OrderStatus();
		Sale sale = new Sale();
		StatusName statusName = new StatusName();
		OrderStatusDTO expected = new OrderStatusDTO("order-status-1", requestDTO.updatedAt(), null);

		when(orderStatusRepository.findById("order-status-1")).thenReturn(Optional.of(orderStatus));
		when(saleRepository.findById("sale-2")).thenReturn(Optional.of(sale));
		when(statusNameRepository.findById(2)).thenReturn(Optional.of(statusName));
		when(orderStatusRepository.save(orderStatus)).thenReturn(orderStatus);
		when(orderStatusMapper.toDto(orderStatus)).thenReturn(expected);

		OrderStatusDTO result = orderStatusService.update("order-status-1", requestDTO);

		assertThat(result).isEqualTo(expected);
		assertThat(orderStatus.getSale()).isSameAs(sale);
		assertThat(orderStatus.getStatusName()).isSameAs(statusName);
		verify(orderStatusMapper).updateEntityFromDto(requestDTO, orderStatus);
		verify(orderStatusRepository).save(orderStatus);
	}

	@Test
	void shouldDeleteOrderStatus() {
		OrderStatus orderStatus = new OrderStatus();

		when(orderStatusRepository.findById("order-status-1")).thenReturn(Optional.of(orderStatus));

		orderStatusService.delete("order-status-1");

		verify(orderStatusRepository).delete(orderStatus);
	}
}
