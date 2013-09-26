package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;

@Component
public class EditDimensionRequest implements RequestModel {
	private MultiDimensionalObject dimensionalObject;

	public EditDimensionRequest() {

	}

	public MultiDimensionalObject getDimensionalObject() {
		return dimensionalObject;
	}

	public void setDimensionalObject(MultiDimensionalObject dimensionalObject) {
		this.dimensionalObject = dimensionalObject;
	}
}
