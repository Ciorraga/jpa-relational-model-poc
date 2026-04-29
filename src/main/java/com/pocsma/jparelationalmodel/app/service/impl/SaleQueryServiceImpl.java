package com.pocsma.jparelationalmodel.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocsma.jparelationalmodel.app.dto.SaleDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.mapper.SaleMapper;
import com.pocsma.jparelationalmodel.app.repository.SaleRepository;
import com.pocsma.jparelationalmodel.app.service.SaleQueryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleQueryServiceImpl implements SaleQueryService {

	private final SaleRepository saleRepository;
	private final SaleMapper saleMapper;
	private final OrderStatusMapper orderStatusMapper;

	@Override
	@Transactional(readOnly = true)
	public List<SaleDTO> findAll() {
		return saleRepository.findAll().stream()
				.map(sale -> saleMapper.toDto(sale, orderStatusMapper))
				.toList();
	}
}
