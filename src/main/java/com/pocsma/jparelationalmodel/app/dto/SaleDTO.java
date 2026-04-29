package com.pocsma.jparelationalmodel.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleDTO(
		String id,
		BigDecimal amount,
		LocalDateTime dateSale,
		ProductDTO product,
		StoreDTO store,
		UserDTO user,
		List<OrderStatusDTO> orderStatuses) {
}
