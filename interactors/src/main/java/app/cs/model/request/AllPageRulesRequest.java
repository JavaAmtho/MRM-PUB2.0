package app.cs.model.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllPageRulesRequest implements RequestModel {

	private List<String> pageIDs;

	public List<String> getPageIDs() {
		return pageIDs;
	}

	public void setPageIDs(List<String> pageIDs) {
		this.pageIDs = pageIDs;
	}

}
