package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entity.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long>{

	public MyOrder findByOrderId(String orderId);
}
