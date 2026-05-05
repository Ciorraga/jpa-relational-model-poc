package com.pocsma.jparelationalmodel.app.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.dto.OrderStatusRequestDTO;
import com.pocsma.jparelationalmodel.app.mapper.OrderStatusMapper;
import com.pocsma.jparelationalmodel.app.model.OrderStatus;
import com.pocsma.jparelationalmodel.app.repository.OrderStatusRepository;
import com.pocsma.jparelationalmodel.app.repository.SaleRepository;
import com.pocsma.jparelationalmodel.app.repository.StatusNameRepository;
import com.pocsma.jparelationalmodel.app.service.OrderStatusService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	private final OrderStatusRepository orderStatusRepository;
	private final SaleRepository saleRepository;
	private final StatusNameRepository statusNameRepository;
	private final OrderStatusMapper orderStatusMapper;

	@Override
	@Transactional(readOnly = true)
	public List<OrderStatusDTO> findAll() {
		return orderStatusRepository.findAll().stream()
				.map(orderStatusMapper::toDto)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public OrderStatusDTO findById(String id) {
		return orderStatusRepository.findById(id)
				.map(orderStatusMapper::toDto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Order status not found: " + id));
	}

	@Override
	@Transactional
	public OrderStatusDTO create(OrderStatusRequestDTO requestDTO) {
		validateCreateRequest(requestDTO);

		OrderStatus orderStatus = orderStatusMapper.toEntity(requestDTO);
		applyRelations(orderStatus, requestDTO);

		return orderStatusMapper.toDto(orderStatusRepository.save(orderStatus));
	}

	@Override
	@Transactional
	public OrderStatusDTO update(String id, OrderStatusRequestDTO requestDTO) {
		validateUpdateRequest(requestDTO);

		OrderStatus orderStatus = findOrderStatus(id);
		orderStatusMapper.updateEntityFromDto(requestDTO, orderStatus);
		applyRelations(orderStatus, requestDTO);

		return orderStatusMapper.toDto(orderStatusRepository.save(orderStatus));
	}

	@Override
	@Transactional
	public void delete(String id) {
		orderStatusRepository.delete(findOrderStatus(id));
	}

	private void validateCreateRequest(OrderStatusRequestDTO requestDTO) {
		if (requestDTO == null || requestDTO.id() == null || requestDTO.saleId() == null
				|| requestDTO.statusNameId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"id, saleId and statusNameId are required");
		}
	}

	private void validateUpdateRequest(OrderStatusRequestDTO requestDTO) {
		if (requestDTO == null || requestDTO.saleId() == null || requestDTO.statusNameId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"saleId and statusNameId are required");
		}
	}

	private void applyRelations(OrderStatus orderStatus, OrderStatusRequestDTO requestDTO) {
		orderStatus.setSale(saleRepository.findById(requestDTO.saleId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Sale not found: " + requestDTO.saleId())));
		orderStatus.setStatusName(statusNameRepository.findById(requestDTO.statusNameId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Status name not found: " + requestDTO.statusNameId())));
	}

	private OrderStatus findOrderStatus(String id) {
		return orderStatusRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Order status not found: " + id));
	}
}
