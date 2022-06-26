package com.kavinduraismay.examples.axonsample.events;

import lombok.Data;

@Data
public class OrderConfirmedEvent {

	private final String orderId;
}
