package com.docdoku.gateway;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.docdoku.products.ProductModel;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/products/v1")
@RegisterRestClient(configKey="products-api")
@Produces("application/json")
public interface ProductsServiceV1 {

	@Path("")
	@GET
	Collection<ProductModel> list();

	@Path("{id}")
	@GET
	@CircuitBreaker(requestVolumeThreshold = 3, failureRatio=0.75, delay = 1000, successThreshold = 2)
	@Timeout(500)
	ProductModel get(@PathParam("id") int productId);
}