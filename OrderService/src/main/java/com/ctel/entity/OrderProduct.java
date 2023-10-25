package com.ctel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sNo;
	
	@Column(name = "prod_name")
	private String prodName;
	
	@Column
	private Integer pid;
	@Column
	private Long qty;
	@Column
	private Double price;

	// @JsonManagedReference
//	oka product ni save chesetappudu aa product ye order ki sanbandhinchinado thelusukovadam kosam order(parent table) object ni oka field laagaaa child table(orderProduct) lo theesukuntaamu
//	map chesukovadam kosam parent table variable ki @JoinColumn  @ManyToOne annotation ni use chesthaamu.
//	@ManyToOne is used to save the "Multiple Products" with "oid" only
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "oid")
	@JsonBackReference
	private Order orderTemp;

	public Integer getsNo() {
		return sNo;
	}

	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	@JsonBackReference
	public Order getOrderTemp() {
		return orderTemp;
	}

	public void setOrderTemp(Order orderTemp) {
		this.orderTemp = orderTemp;
	}

	public OrderProduct(Integer sNo, String prodName, Integer pid, Long qty, Double price, Order orderTemp) {
		super();
		this.sNo = sNo;
		this.prodName = prodName;
		this.pid = pid;
		this.qty = qty;
		this.price = price;
		this.orderTemp = orderTemp;
	}

	public OrderProduct() {
		super();
	}
	
	

}
