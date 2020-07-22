package com.docdoku.gateway;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.docdoku.orders.OrderModel;
import com.docdoku.products.ProductModel;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v2")
@Produces(MediaType.APPLICATION_JSON)
public class GatewayResourceV2 {

	@Context
	private SecurityContext securityContext;

	@Inject @RestClient
	// Package visibility for better performance when running Natively
	// https://quarkus.io/guides/cdi-reference#native-executables-and-private-members
	ProductsServiceV1 productsService;

	@Inject @RestClient
	AccountsServiceV1 accountsService;

	@Inject @RestClient
	OrdersServiceV1 ordersService;

	@Inject @Channel("order-created")
	Emitter<SagaOrderModel> sagaEmitter;

	@POST
	@Path("orders")
	public OrderModel createOrder(OrderModel order) {
		// Get the product or fail
		ProductModel product = productsService.get(order.productId);
		
		// Create a pending order
		order = this.ordersService.create(order);

		// Send saga message
		// Warning: this is not safe! Kafka might be not available
		// after the order has been created
		// Sending the message should be done asynchronously
		// by polling the pending orders or the DB logs (using Debezium for instance)
		System.out.println("--- 1. Created order " + order.id);
		SagaOrderModel saga = new SagaOrderModel();
		saga.orderId = order.id;
		saga.transactionAmount = product.price * order.quantity;
		saga.accountId = getUserId();
		this.sagaEmitter.send(saga);

		return order;
	}

	private int getUserId() {
		// TODO: get user id from Authorization header
		return 1;
	}
}