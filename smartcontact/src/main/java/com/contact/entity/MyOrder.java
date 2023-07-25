package com.contact.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class MyOrder {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Long myOId;
	
	private String orderId;
	
	private String amount;
	
	private String receipt;
	
	private String status;
	
	private String payementId;
	
	@ManyToOne
	private User user;

	public MyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyOrder(Long myOId, String orderId, String amount, String receipt, String status, String payementId,
			User user) {
		super();
		this.myOId = myOId;
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.payementId = payementId;
		this.user = user;
	}

	public Long getMyOId() {
		return myOId;
	}

	public void setMyOId(Long myOId) {
		this.myOId = myOId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayementId() {
		return payementId;
	}

	public void setPayementId(String payementId) {
		this.payementId = payementId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
