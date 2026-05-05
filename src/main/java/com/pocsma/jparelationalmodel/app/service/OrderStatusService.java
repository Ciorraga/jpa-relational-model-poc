package com.pocsma.jparelationalmodel.app.service;

import java.util.List;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.dto.OrderStatusRequestDTO;

public interface OrderStatusService {

	List<OrderStatusDTO> findAll();

	OrderStatusDTO findById(String id);

	OrderStatusDTO create(OrderStatusRequestDTO requestDTO);

	OrderStatusDTO update(String id, OrderStatusRequestDTO requestDTO);

	void delete(String id);
}
