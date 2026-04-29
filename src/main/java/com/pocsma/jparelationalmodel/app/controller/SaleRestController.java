package com.pocsma.jparelationalmodel.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocsma.jparelationalmodel.app.dto.SaleDTO;
import com.pocsma.jparelationalmodel.app.service.SaleQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "Sales", description = "Read sale aggregates")
public class SaleRestController {

	private final SaleQueryService saleQueryService;

	@Operation(summary = "List sales")
	@GetMapping("/sales")
	public List<SaleDTO> getAllSales() {
		return saleQueryService.findAll();
	}
}
