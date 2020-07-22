package com.docdoku.orders;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class OrdersHealthCheck implements HealthCheck {

	Random random = new Random();

	@Override
	public HealthCheckResponse call() {
		boolean ok = random.nextBoolean();
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named(ok ? "Orders OK" : "Orders KO");
		responseBuilder = ok
			? responseBuilder.up().withData("dbconnection", "Connection success")
			: responseBuilder.down().withData("dbconnection", "Connection failure");
		return responseBuilder.build();
	}
}