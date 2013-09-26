package app.cs.model.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class PIMOrMAMNode implements ResponseModel {

	@Override
	public String toString() {
		return "PIMNode [id=" + id + ", title=" + label + ", isfolder="
				+ isfolder + ", type=" + type + ", image=" + image
				+ ", description=" + description + ", service=" + service
				+ ", isLazy=" + isLazy + "]";
	}

	private String id;

	@JsonProperty(value = "label")
	private String label;
	private String title;
	private boolean isfolder;
	private String type;
	private String image;
	private String description;
	private String service;
	private boolean isLazy;
	private boolean isFolder;

	public boolean isIsFolder() {
		return isfolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public boolean getIsLazy() {
		return isfolder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return label;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isIsfolder() {
		return isfolder;
	}

	public void setIsfolder(boolean isfolder) {
		this.isfolder = isfolder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

}
