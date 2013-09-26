package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class CreatePageRequest implements RequestModel {

	private String type;
	private String name;
	private String path;
	private boolean isFolder;

	public CreatePageRequest() {

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

	public CreatePageRequest(String type, String name, String path,
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

	/**
	 * 
	 * @return true if page object else false if page object
	 */
	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

}
