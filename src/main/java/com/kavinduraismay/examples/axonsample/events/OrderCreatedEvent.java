package com.kavinduraismay.examples.axonsample.events;

import lombok.Data;

@Data
public class OrderCreatedEvent {
	
	private final String orderId;
	private final String productId;

}
