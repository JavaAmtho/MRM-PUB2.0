package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.Assortment;

@Component
public class CreateAssortmentRequest implements RequestModel {

	private Assortment assortment;
	private String path;
	private String name;

	public Assortment getAssortment() {
		return assortment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAssortment(Assortment assortment) {
		this.assortment = assortment;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	

}
