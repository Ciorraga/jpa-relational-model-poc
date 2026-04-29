package com.pocsma.jparelationalmodel.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pocsma.jparelationalmodel.app.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {

	@Override
	@EntityGraph(attributePaths = {"statusName"})
	List<OrderStatus> findAll();
}
