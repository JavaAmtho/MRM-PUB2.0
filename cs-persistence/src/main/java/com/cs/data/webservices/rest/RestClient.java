package com.cs.data.webservices.rest;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.cs.data.api.webservices.rest.IRestClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

@Component
public class RestClient implements IRestClient {

	@Override
	public String get(String url, Map<String, String> headerParameters) {
		ClientResponse response = getResource(url, headerParameters).get(
				ClientResponse.class);
		return getSuccessResponse(url, response);
	}

	@Override
	public ClientResponse post(String url,
			Map<String, String> headerParameters, String input) {

		WebResource webResource = getClient().resource(url);
		if (headerParameters != null) {
			Set<String> keySet = headerParameters.keySet();

			Builder requestBuilder = webResource.getRequestBuilder();
			for (String key : keySet) {
				requestBuilder.header(key, headerParameters.get(key));
			}
		}

		ClientResponse response = webResource.type("application/json").post(
				ClientResponse.class, input);
		return response;
	}

	protected String getSuccessResponse(String url, ClientResponse response) {
		return response.getEntity(String.class);
	}

	protected Builder getResource(String url,
			Map<String, String> headerParameters) {
		WebResource webResource = getClient().resource(url);
		if (headerParameters != null) {
			Set<String> keySet = headerParameters.keySet();

			Builder requestBuilder = webResource.getRequestBuilder();
			for (String key : keySet) {
				requestBuilder.header(key, headerParameters.get(key));
			}
			return requestBuilder.accept(MediaType.APPLICATION_JSON_TYPE);
		}
		return webResource.accept(MediaType.APPLICATION_JSON_TYPE);
	}

	protected Client getClient() {
		return Client.create();
	}
}
