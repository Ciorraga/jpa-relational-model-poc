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
@Table(name = "status_name")
public class StatusName implements Serializable {

	private static final long serialVersionUID = 5901612175618837975L;

	@Id
	@Column(name = "status_name_id", nullable = false)
	private Integer statusNameId;

	@Column(name = "status_name", nullable = false)
	private String name;
}
