 package com.ctel.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "orderTemp")
	@JsonManagedReference
	private List<OrderProduct> prodList;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdDate;
	
	private Integer cid;
	private Double bill;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
//	@JsonManagedReference
	public List<OrderProduct> getProdList() {
		return prodList;
	}
	public void setProdList(List<OrderProduct> prodList) {
		this.prodList = prodList;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Double getBill() {
		return bill;
	}
	public void setBill(Double bill) {
		this.bill = bill;
	}
	public Order(Integer oid, List<OrderProduct> prodList, LocalDateTime createdDate, Integer cid, Double bill) {
		super();
		this.oid = oid;
		this.prodList = prodList;
		this.createdDate = createdDate;
		this.cid = cid;
		this.bill = bill;
	}
	public Order() {
		super();
	}
	
	
	

}
