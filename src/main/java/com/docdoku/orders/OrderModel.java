package com.docdoku.orders;

import java.time.Instant;

public class OrderModel {

	public enum State {
		PENDING, CREATED
	}

	public int id;
	public Instant date;
	public int accountId;
	public int productId;
	public int quantity;
	public State state = State.PENDING;
	
	public OrderModel() {
		this.id = -1;
		this.date = Instant.now();
	}
	public OrderModel(int id, OrderModel order) {
		this.id = id;
		this.date = order.date;
		this.accountId = order.accountId;
		this.productId = order.productId;
		this.quantity = order.quantity;
	}
}