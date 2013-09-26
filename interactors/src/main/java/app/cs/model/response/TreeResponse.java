package app.cs.model.response;

import java.util.List;

import app.cs.impl.model.MultiDimensionalObject;

public class TreeResponse implements ResponseModel {
	
	public TreeResponse(List<MultiDimensionalObject> tree) {
		super();
		this.tree = tree;
	}

	List<MultiDimensionalObject> tree;

	public List<MultiDimensionalObject> getTree() {
		return tree;
	}

	public void setTree(List<MultiDimensionalObject> tree) {
		this.tree = tree;
	}
}
