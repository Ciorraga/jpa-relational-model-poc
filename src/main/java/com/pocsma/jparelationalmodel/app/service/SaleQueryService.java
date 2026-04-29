package com.pocsma.jparelationalmodel.app.service;

import java.util.List;

import com.pocsma.jparelationalmodel.app.dto.SaleDTO;

public interface SaleQueryService {

	List<SaleDTO> findAll();
}
