package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;

@Component
public class MoveDimensionRequest implements RequestModel {

	private MultiDimensionalObject objectInMove;
	private String newPath;
	private String oldPath;

	public MultiDimensionalObject getObjectInMove() {
		return objectInMove;
	}

	public void setObjectInMove(MultiDimensionalObject objectInMove) {
		this.objectInMove = objectInMove;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getOldPath() {
		return oldPath;
	}

	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}

}
