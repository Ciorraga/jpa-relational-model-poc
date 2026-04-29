package com.pocsma.jparelationalmodel.app.service;

import java.util.List;

import com.pocsma.jparelationalmodel.app.dto.StoreDTO;

public interface StoreQueryService {

	List<StoreDTO> findAll();
}
