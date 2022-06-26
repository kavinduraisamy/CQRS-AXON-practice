package com.kavinduraismay.examples.axonsample;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import com.kavinduraismay.examples.axonsample.aggregate.OrderAggregate;
import com.kavinduraismay.examples.axonsample.commands.CreateOrderCommand;
import com.kavinduraismay.examples.axonsample.commands.ShipOrderCommand;
import com.kavinduraismay.examples.axonsample.events.OrderConfirmedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderCreatedEvent;
import com.kavinduraismay.examples.axonsample.events.OrderShippedEvent;




class AxonSampleApplicationTests {

	private FixtureConfiguration<OrderAggregate> fixture;

	@Before
	public void setUp() {
	    fixture = new AggregateTestFixture<>(OrderAggregate.class);
	}
	
	@Test
	public void createOrderTest() {
		String orderId = UUID.randomUUID().toString();
		String productId = "LG Monitor";
		fixture.givenNoPriorActivity()
		  .when(new CreateOrderCommand(orderId, productId))
		  .expectEvents(new OrderCreatedEvent(orderId, productId));
	}
	
	@Test
	public void orderShippWithoutConfirmTest() {
		String orderId = UUID.randomUUID().toString();
		String productId = "LG Monitor";
		fixture.given(new OrderCreatedEvent(orderId, productId))
		  .when(new ShipOrderCommand(orderId))
		  .expectException(Exception.class);
	}
	
	@Test
	public void shipConfirmedOrderTest() {
		
		String orderId = UUID.randomUUID().toString();
		String productId = "LG Monitor";
		fixture.given(new OrderCreatedEvent(orderId, productId), new OrderConfirmedEvent(orderId))
		  .when(new ShipOrderCommand(orderId))
		  .expectEvents(new OrderShippedEvent(orderId));
		
	}
	
	

}
