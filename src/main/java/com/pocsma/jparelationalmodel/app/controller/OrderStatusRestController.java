package com.pocsma.jparelationalmodel.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pocsma.jparelationalmodel.app.dto.OrderStatusDTO;
import com.pocsma.jparelationalmodel.app.dto.OrderStatusRequestDTO;
import com.pocsma.jparelationalmodel.app.service.OrderStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "Order Statuses", description = "Read order status history")
public class OrderStatusRestController {

	private final OrderStatusService orderStatusService;

	@Operation(summary = "List order statuses")
	@GetMapping("/order-statuses")
	public List<OrderStatusDTO> getAllOrders() {
		return orderStatusService.findAll();
	}

	@Operation(summary = "Get order status by id")
	@GetMapping("/order-statuses/{id}")
	public OrderStatusDTO getOrderStatusById(@PathVariable String id) {
		return orderStatusService.findById(id);
	}

	@Operation(summary = "Create order status")
	@PostMapping("/order-statuses")
	@ResponseStatus(HttpStatus.CREATED)
	public OrderStatusDTO createOrderStatus(@RequestBody OrderStatusRequestDTO requestDTO) {
		return orderStatusService.create(requestDTO);
	}

	@Operation(summary = "Update order status")
	@PutMapping("/order-statuses/{id}")
	public OrderStatusDTO updateOrderStatus(@PathVariable String id, @RequestBody OrderStatusRequestDTO requestDTO) {
		return orderStatusService.update(id, requestDTO);
	}

	@Operation(summary = "Delete order status")
	@DeleteMapping("/order-statuses/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrderStatus(@PathVariable String id) {
		orderStatusService.delete(id);
	}
}
