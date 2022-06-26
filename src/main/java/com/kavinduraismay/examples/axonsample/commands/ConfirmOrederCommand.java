package com.kavinduraismay.examples.axonsample.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class ConfirmOrederCommand {
	
	@TargetAggregateIdentifier
	private final String orderId;
	

}
