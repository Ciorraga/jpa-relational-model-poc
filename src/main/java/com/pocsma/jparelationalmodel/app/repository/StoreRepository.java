package com.pocsma.jparelationalmodel.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pocsma.jparelationalmodel.app.model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

	@Override
	@EntityGraph(attributePaths = {"city", "city.country"})
	List<Store> findAll();
}
