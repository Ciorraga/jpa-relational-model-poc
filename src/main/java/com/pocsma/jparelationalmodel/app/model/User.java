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
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 2214981045140252170L;

	@Id
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "name", nullable = false)
	private String name;
}
