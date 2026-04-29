package com.pocsma.jparelationalmodel.app.service;

import java.util.List;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;

public interface OrderStatusQueryService {

	List<OrderStatusDTO> findAll();
}
