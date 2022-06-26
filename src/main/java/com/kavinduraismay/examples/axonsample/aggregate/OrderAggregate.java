package com.kavinduraismay.examples.axonsample.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.kavinduraismay.examples.axonsample.commands.ConfirmOrederCommand;
import com.kavinduraismay.examples.axonsample.commands.CreateOrderCommand;
import com.kavinduraismay.examples.axonsample.commands.ShipOrderCommand;
import com.kavinduraismay.examples.axonsample.events.OrderConfirmedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderCreatedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderShippedEvent;

@Aggregate
public class OrderAggregate {
	
	@AggregateIdentifier
	public String orderId;
	public boolean orderConfirmed;
	
	
	
	protected OrderAggregate() {}
	
	
	@CommandHandler
	public OrderAggregate(CreateOrderCommand createOrderCommand) {
		System.out.println("CreateOrderCommand called "+createOrderCommand.getOrderId());
		AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getOrderId(),createOrderCommand.getProductId()));
		
	}
	
	@EventSourcingHandler
	public void on(OrderCreatedEvent orderCreatedEvent) {
		this.orderId=orderCreatedEvent.getOrderId();
		this.orderConfirmed=false;
	}


	@CommandHandler
	public void handle(ConfirmOrederCommand confirmOrederCommand) {
		if(orderConfirmed) {
			return;
		}
		AggregateLifecycle.apply(new OrderConfirmedEvent(confirmOrederCommand.getOrderId()));
	}
	
	@EventSourcingHandler
	public void on(OrderConfirmedEvent orderConfirmedEvent) {
		orderConfirmed=true;
	}
	
	@CommandHandler
	public void handle(ShipOrderCommand shipOrderCommand) throws Exception {
		if(!orderConfirmed) {
			throw new Exception("order not confirmed");
		}
		
		AggregateLifecycle.apply(new OrderShippedEvent(shipOrderCommand.getOrderId()));
	}
	
}
