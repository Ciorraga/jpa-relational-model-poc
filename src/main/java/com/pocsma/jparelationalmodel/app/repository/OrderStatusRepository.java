package com.pocsma.jparelationalmodel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocsma.jparelationalmodel.app.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{

}
