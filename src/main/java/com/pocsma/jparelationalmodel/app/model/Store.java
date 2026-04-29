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
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 4057063708452463517L;

	@Id
	@Column(name = "store_id", nullable = false)
	private Integer storeId;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private City city;
}
