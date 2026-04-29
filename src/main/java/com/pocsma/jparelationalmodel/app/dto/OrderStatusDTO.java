package com.pocsma.jparelationalmodel.app.dto;

import java.time.LocalDateTime;

public record OrderStatusDTO(String id, LocalDateTime updatedAt, StatusNameDTO statusName) {
}
