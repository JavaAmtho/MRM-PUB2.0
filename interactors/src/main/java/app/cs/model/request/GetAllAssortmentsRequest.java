package app.cs.model.request;

import org.springframework.stereotype.Component;

@Component
public class GetAllAssortmentsRequest implements RequestModel {

	private String pagePath;
	private String logicalPageID;

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getLogicalPageID() {
		return logicalPageID;
	}

	public void setLogicalPageID(String logicalPageID) {
		this.logicalPageID = logicalPageID;
	}

}
