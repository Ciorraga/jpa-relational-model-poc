package com.pocsma.jparelationalmodel.app.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 2391788910195475031L;

	@Id
	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "name", nullable = false)
	private String name;
}
