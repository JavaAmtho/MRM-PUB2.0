package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;

@Component
public class DeleteDimensionRequest implements RequestModel {

	private MultiDimensionalObject dimension;

	public MultiDimensionalObject getDimension() {
		return dimension;
	}

	public void setDimension(MultiDimensionalObject dimension) {
		this.dimension = dimension;
	}
}
