package com.pocsma.jparelationalmodel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocsma.jparelationalmodel.app.model.StatusName;

public interface StatusNameRepository extends JpaRepository<StatusName, Integer> {
}
