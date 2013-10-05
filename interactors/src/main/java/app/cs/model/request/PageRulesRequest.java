package app.cs.model.request;

import java.util.List;

import app.cs.impl.model.PageRule;

public class PageRulesRequest implements RequestModel{

	private List<PageRule> pageRules;
	private String logicalPageID;
	
	public List<PageRule> getPageRules() {
		return pageRules;
	}
	
	public void setPageRules(List<PageRule> pageRules) {
		this.pageRules = pageRules;
	}

	public String getLogicalPageID() {
		return logicalPageID;
	}

	public void setLogicalPageID(String logicalPageID) {
		this.logicalPageID = logicalPageID;
	}

	

	

}
