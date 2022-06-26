package com.kavinduraismay.examples.axonsample.query.model;

import lombok.Data;

@Data
public class Order {
	
	private String orderId;
	private String productId;
	private OrderStatus orderStatus;
	
	public Order(String orderId,String productId) {
		this.orderId=orderId;
		this.productId=productId;
		this.orderStatus=OrderStatus.CREATED;
	}
	
	
	public void setOrderConfirmed() {
		this.orderStatus=OrderStatus.CONFIRMED;
		
	}
	
	public void setOrderShipped() {
		this.orderStatus=OrderStatus.SHIPPED;
	}

}
