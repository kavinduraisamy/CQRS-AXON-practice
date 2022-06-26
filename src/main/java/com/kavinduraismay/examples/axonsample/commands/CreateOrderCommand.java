package com.kavinduraismay.examples.axonsample.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class CreateOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;
	private final String productId;

}
