package com.docdoku.orders;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderDeserializer extends JsonbDeserializer<OrderModel> {
	public OrderDeserializer() {
		super(OrderModel.class);
	}
}