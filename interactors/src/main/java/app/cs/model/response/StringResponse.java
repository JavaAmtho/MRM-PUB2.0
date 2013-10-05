package app.cs.model.response;

public class StringResponse implements ResponseModel {
	private String responseString;

	public StringResponse(String responseString) {
		super();
		this.responseString = responseString;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
