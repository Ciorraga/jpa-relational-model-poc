package com.pocsma.jparelationalmodel.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocsma.jparelationalmodel.app.dto.StoreDTO;
import com.pocsma.jparelationalmodel.app.service.StoreQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "Stores", description = "Read stores and their location hierarchy")
public class StoreRestController {

	private final StoreQueryService storeQueryService;

	@Operation(summary = "List stores")
	@GetMapping("/stores")
	public List<StoreDTO> getAllStores() {
		return storeQueryService.findAll();
	}
}
