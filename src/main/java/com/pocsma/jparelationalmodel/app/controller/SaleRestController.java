package com.pocsma.jparelationalmodel.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocsma.jparelationalmodel.app.model.Sale;
import com.pocsma.jparelationalmodel.app.repository.SaleRepository;

@RestController
public class SaleRestController{
	
	@Autowired
	private SaleRepository saleRepository;

	@GetMapping("/sales")
	public ResponseEntity<List<Sale>> getAllSales() {
		List<Sale> sales = saleRepository.findAll();
		
		return !sales.isEmpty() ? new ResponseEntity<>(sales, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
