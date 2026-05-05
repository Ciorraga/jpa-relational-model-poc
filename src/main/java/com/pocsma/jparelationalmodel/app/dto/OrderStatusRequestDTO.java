package com.pocsma.jparelationalmodel.app.dto;

import java.time.LocalDateTime;

public record OrderStatusRequestDTO(String id, LocalDateTime updatedAt, String saleId, Integer statusNameId) {
}
