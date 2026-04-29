package com.pocsma.jparelationalmodel.app.model;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City implements Serializable {

	private static final long serialVersionUID = 5264850844662593793L;

	@Id
	@Column(name = "city_id", nullable = false)
	private Integer cityId;

	@Column(name = "city_name", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Country country;
}
