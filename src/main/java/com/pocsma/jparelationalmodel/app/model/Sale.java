package com.pocsma.jparelationalmodel.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sale")
public class Sale implements Serializable {

	private static final long serialVersionUID = -335857734346264194L;

	@Id
	@Column(name = "sale_id", nullable = false)
	private String saleId;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "date_sale")
	private LocalDateTime dateSale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@JsonManagedReference
	@OneToMany(mappedBy = "sale")
	private List<OrderStatus> orderStatuses = new ArrayList<>();
}
