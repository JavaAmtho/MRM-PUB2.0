package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class WBDCreationAndPlanningRequest implements RequestModel {

	private String ruleID;
	private String logicalPageID;
	private String publicationID;

	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	public String getLogicalPageID() {
		return logicalPageID;
	}

	public void setLogicalPageID(String logicalPageID) {
		this.logicalPageID = logicalPageID;
	}

	public String getPublicationID() {
		return publicationID;
	}

	public void setPublicationID(String publicationID) {
		this.publicationID = publicationID;
	}

}
