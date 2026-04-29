package com.pocsma.jparelationalmodel.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocsma.jparelationalmodel.app.dto.StoreDTO;
import com.pocsma.jparelationalmodel.app.mapper.StoreMapper;
import com.pocsma.jparelationalmodel.app.repository.StoreRepository;
import com.pocsma.jparelationalmodel.app.service.StoreQueryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreQueryServiceImpl implements StoreQueryService {

	private final StoreRepository storeRepository;
	private final StoreMapper storeMapper;

	@Override
	@Transactional(readOnly = true)
	public List<StoreDTO> findAll() {
		return storeRepository.findAll().stream()
				.map(storeMapper::toDto)
				.toList();
	}
}
