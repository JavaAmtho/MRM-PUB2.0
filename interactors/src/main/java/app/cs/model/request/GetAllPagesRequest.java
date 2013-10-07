package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;

@Component
public class GetAllPagesRequest implements RequestModel {

	private String publicationId;
	private MultiDimensionalObject publication;

	public String getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}

	public MultiDimensionalObject getPublication() {
		return publication;
	}

	public void setPublication(MultiDimensionalObject publication) {
		this.publication = publication;
	}

}
