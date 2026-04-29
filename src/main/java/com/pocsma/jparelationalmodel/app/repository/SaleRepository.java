package com.pocsma.jparelationalmodel.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pocsma.jparelationalmodel.app.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {

	@Override
	@EntityGraph(attributePaths = {"product", "store.city.country", "user", "orderStatuses", "orderStatuses.statusName"})
	List<Sale> findAll();
}
