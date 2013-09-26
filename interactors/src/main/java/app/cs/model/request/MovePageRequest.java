package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class MovePageRequest implements RequestModel {

	private String type;
	private String name;
	private String path;
	private String newPath;
	
	public MovePageRequest(){
		
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

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	private boolean isFolder;

	public MovePageRequest(String type, String name, String path,
			boolean isFolder, String newPath) {
		super();
		this.type = type;
		this.name = name;
		this.path = path;
		this.isFolder = isFolder;
		this.newPath = newPath;
	}

}
