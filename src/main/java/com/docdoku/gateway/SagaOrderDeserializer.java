package com.docdoku.gateway;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class SagaOrderDeserializer extends JsonbDeserializer<SagaOrderModel> {
	public SagaOrderDeserializer() {
		super(SagaOrderModel.class);
	}
}	