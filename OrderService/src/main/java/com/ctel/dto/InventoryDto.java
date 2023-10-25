package com.ctel.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class InventoryDto {
	
	
	private Integer iid;

	private String prodName;

	private Integer prodId;

	private Integer stockOut;

//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@Column(name = "recorded_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime recordedDate;

	private Long qty;

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getStockOut() {
		return stockOut;
	}

	public void setStockOut(Integer stockOut) {
		this.stockOut = stockOut;
	}

	public LocalDateTime getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(LocalDateTime recordedDate) {
		this.recordedDate = recordedDate;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}
	
	
}
