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
@Table(name = "country")
public class Country implements Serializable {

	private static final long serialVersionUID = 8807215633600118386L;

	@Id
	@Column(name = "country_id", nullable = false)
	private Integer countryId;

	@Column(name = "country_name", nullable = false)
	private String name;
}
