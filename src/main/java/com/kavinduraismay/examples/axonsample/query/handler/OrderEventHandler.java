package com.kavinduraismay.examples.axonsample.query.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.kavinduraismay.examples.axonsample.events.OrderConfirmedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderCreatedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderShippedEvent;
import com.kavinduraismay.examples.axonsample.query.FindAllOrderQuery;
import com.kavinduraismay.examples.axonsample.query.model.Order;
import com.kavinduraismay.examples.axonsample.query.model.OrderStatus;

@Service
public class OrderEventHandler {
	
 private final Map<String, Order> orders=new HashMap<String,Order>();
 
 @EventHandler
 public void on(OrderCreatedEvent orderCreatedEvent) {
	 String orderId=orderCreatedEvent.getOrderId();
	 orders.put(orderId, new Order(orderId, orderCreatedEvent.getProductId()));
	 System.out.println("order cretae "+orderId);
 }
 
 
 @EventHandler
 public void on(OrderConfirmedEvent orderConfirmedEvent) {
	 String orderId=orderConfirmedEvent.getOrderId();
	 if(orders.containsKey(orderId)) {
		 Order order=orders.get(orderId);
		 order.setOrderStatus(OrderStatus.CONFIRMED);
		 System.out.println("order confirmed "+orderId);
	 }
 }
 
 
 @EventHandler
 public void on(OrderShippedEvent orderShippedEvent) {
	 String orderId=orderShippedEvent.getOrderId();
	 if(orders.containsKey(orderId)) {
		 Order order=orders.get(orderId);
		 order.setOrderStatus(OrderStatus.SHIPPED);
		 System.out.println("order shipped "+orderId);
	 }
 }
 
 
 @QueryHandler
 public List<Order> on(FindAllOrderQuery findAllOrderQuery) {
	 return new ArrayList<>(orders.values());
 }

}
