package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.Assortment;

@Component
public class CopyAssortmentRequest implements RequestModel {

	private Assortment assortment;
	private String newPath;
	private String newName;

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public Assortment getAssortment() {
		return assortment;
	}

	public void setAssortment(Assortment assortment) {
		this.assortment = assortment;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

}
