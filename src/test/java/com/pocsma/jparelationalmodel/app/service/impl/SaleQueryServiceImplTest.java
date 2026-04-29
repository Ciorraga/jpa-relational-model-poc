package com.pocsma.jparelationalmodel.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pocsma.jparelationalmodel.app.dto.SaleDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.mapper.SaleMapper;
import com.pocsma.jparelationalmodel.app.model.Sale;
import com.pocsma.jparelationalmodel.app.repository.SaleRepository;

@ExtendWith(MockitoExtension.class)
class SaleQueryServiceImplTest {

	@Mock
	private SaleRepository saleRepository;

	@Mock
	private SaleMapper saleMapper;

	@Mock
	private OrderStatusMapper orderStatusMapper;

	@InjectMocks
	private SaleQueryServiceImpl saleQueryService;

	@Test
	void shouldReturnMappedSales() {
		Sale sale = new Sale();
		sale.setSaleId("sale-1");

		SaleDTO saleDTO = new SaleDTO(
				"sale-1",
				BigDecimal.TEN,
				LocalDateTime.of(2026, 4, 29, 10, 0),
				null,
				null,
				null,
				List.of());

		when(saleRepository.findAll()).thenReturn(List.of(sale));
		when(saleMapper.toDto(sale, orderStatusMapper)).thenReturn(saleDTO);

		List<SaleDTO> result = saleQueryService.findAll();

		assertThat(result).containsExactly(saleDTO);
		verify(saleRepository).findAll();
		verify(saleMapper).toDto(sale, orderStatusMapper);
	}
}
