package app.cs.impl.model;

import java.io.Serializable;
import java.util.List;

import com.cs.data.api.core.GenericDomain;


public class Assortment implements GenericDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;
	
	private String id;

	public List<Product> getProducts() {
		return products;
	}

	public String getID() {
		return id;
	}

	public void setID(String iD) {
		id = iD;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
