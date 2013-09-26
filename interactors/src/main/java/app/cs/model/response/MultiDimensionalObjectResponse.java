package app.cs.model.response;

import app.cs.impl.model.MultiDimensionalObject;

public class MultiDimensionalObjectResponse implements ResponseModel {
	private MultiDimensionalObject response;

	public MultiDimensionalObjectResponse(MultiDimensionalObject response) {
		super();
		this.response = response;
	}

	public MultiDimensionalObject getResponse() {
		return response;
	}

	public void setResponseString(MultiDimensionalObject response) {
		this.response = response;
	}
}
