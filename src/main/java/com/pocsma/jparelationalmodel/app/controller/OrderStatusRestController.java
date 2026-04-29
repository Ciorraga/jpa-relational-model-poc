package com.pocsma.jparelationalmodel.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.service.OrderStatusQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "Order Statuses", description = "Read order status history")
public class OrderStatusRestController {

	private final OrderStatusQueryService orderStatusQueryService;

	@Operation(summary = "List order statuses")
	@GetMapping("/order-statuses")
	public List<OrderStatusDTO> getAllOrders() {
		return orderStatusQueryService.findAll();
	}
}
