package com.cs.data.api.webservices.rest;

import java.util.Map;

public interface IRestClient {

	public abstract String get(String url, Map<String, String> headerParameters);

}