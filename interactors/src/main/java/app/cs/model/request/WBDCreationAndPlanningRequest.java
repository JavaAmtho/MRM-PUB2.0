package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class WBDCreationAndPlanningRequest implements RequestModel {

	private String templateID;
	private String assortmentID;
	private String parentID;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getAssortmentID() {
		return assortmentID;
	}

	public void setAssortmentID(String assortmentID) {
		this.assortmentID = assortmentID;
	}

}
