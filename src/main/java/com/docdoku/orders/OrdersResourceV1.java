package com.docdoku.orders;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.docdoku.common.MakeSlow;
import com.docdoku.common.SimulateNetworkFailure;
import com.docdoku.gateway.SagaOrderModel;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@Path("/orders/v1")
public class OrdersResourceV1 {

	@Inject
	OrdersRepository orders;

	@GET
	public Collection<OrderModel> list() {
		return this.orders.getOrders();
	}
	
	@GET
	@Path("{id}")
	public OrderModel get(@PathParam("id") int id) {
		OrderModel order = orders.getOrder(id);
		if (order == null) throw new NotFoundException();
		return order;
	}
	
	@DELETE
	public void clear() {
		orders.clearOrders();
	}
	
	@POST
	@SimulateNetworkFailure
	@MakeSlow
	public OrderModel create(OrderModel order) {
		return this.orders.createOrder(order);
	}

	/**
	 * Called when the account has been debited.
	 * Here we finalize the order process by setting
	 * the state of the order.
	 */
	@Incoming("account-debited")
	public void onAccountDebited(SagaOrderModel saga) {
		System.out.println("--- 3. Order finalized");
		OrderModel order = this.orders.getOrder(saga.orderId);
		order.state = OrderModel.State.CREATED;
		this.orders.saveOrder(order);
	}
}
