package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class StringRequest implements RequestModel {
	private String stringRequest;

	public StringRequest() {

	}

	public String getStringRequest() {
		return stringRequest;
	}

	public void setStringRequest(String stringRequest) {
		this.stringRequest = stringRequest;
	}

	public StringRequest(String stringRequest) {
		super();
		this.stringRequest = stringRequest;
	}

}
