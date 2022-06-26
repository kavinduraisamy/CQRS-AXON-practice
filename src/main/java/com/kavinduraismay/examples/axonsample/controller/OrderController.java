package com.kavinduraismay.examples.axonsample.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kavinduraismay.examples.axonsample.commands.ConfirmOrederCommand;
import com.kavinduraismay.examples.axonsample.commands.CreateOrderCommand;
import com.kavinduraismay.examples.axonsample.commands.ShipOrderCommand;
import com.kavinduraismay.examples.axonsample.query.FindAllOrderQuery;
import com.kavinduraismay.examples.axonsample.query.model.Order;

@RestController
public class OrderController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryGateway queryGateway;

	@PostMapping("/shipOrder")
	public CompletableFuture<Void> shipOrder() {
		String orderId = UUID.randomUUID().toString();
		return commandGateway.send(new CreateOrderCommand(orderId, "LG Monitor"))
				.thenCompose(resutl -> commandGateway.send(new ConfirmOrederCommand(orderId)))
				.thenCompose(result -> commandGateway.send(new ShipOrderCommand(orderId)));
	}
	
	
	
	@GetMapping("/orders")
	public CompletableFuture<List<Order>> getAllOrders() {
		return queryGateway.query(new FindAllOrderQuery(), ResponseTypes.multipleInstancesOf(Order.class));
	}

}
