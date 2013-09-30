package com.cs.data.api.webservices.rest;

import java.util.Map;

import com.sun.jersey.api.client.ClientResponse;

public interface IRestClient {

	public abstract String get(String url, Map<String, String> headerParameters);

	public abstract ClientResponse post(String url,
			Map<String, String> headerParameters, String input);
}