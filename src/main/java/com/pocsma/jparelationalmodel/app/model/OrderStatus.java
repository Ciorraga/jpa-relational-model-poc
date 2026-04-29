package com.pocsma.jparelationalmodel.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "order_status")
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 5497999890177664882L;

	@Id
	@Column(name = "order_status_id", nullable = false)
	private String orderStatusId;

	@Column(name = "update_at")
	private LocalDateTime updatedAt;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Sale sale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_name_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private StatusName statusName;
}
