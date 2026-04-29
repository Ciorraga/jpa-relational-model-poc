package com.pocsma.jparelationalmodel.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.repository.OrderStatusRepository;
import com.pocsma.jparelationalmodel.app.service.OrderStatusQueryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderStatusQueryServiceImpl implements OrderStatusQueryService {

	private final OrderStatusRepository orderStatusRepository;
	private final OrderStatusMapper orderStatusMapper;

	@Override
	@Transactional(readOnly = true)
	public List<OrderStatusDTO> findAll() {
		return orderStatusRepository.findAll().stream()
				.map(orderStatusMapper::toDto)
				.toList();
	}
}
