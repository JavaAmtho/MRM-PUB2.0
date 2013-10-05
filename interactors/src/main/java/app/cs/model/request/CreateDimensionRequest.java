package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.DimensionInfo;

@Component
public class CreateDimensionRequest implements RequestModel {

	private String type;
	private String name;
	private String path;

	private DimensionInfo dimensionInfo;

	public DimensionInfo getDimensionInfo() {
		return dimensionInfo;
	}

	public void setDimensionInfo(DimensionInfo dimensionInfo) {
		this.dimensionInfo = dimensionInfo;
	}

	public CreateDimensionRequest() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public CreateDimensionRequest(String type, String name, String path,
			boolean isFolder) {
		super();
		this.type = type;
		this.name = name;
		this.path = path;
		this.isFolder = isFolder;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	private boolean isFolder;

}
