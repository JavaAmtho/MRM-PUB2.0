package app.cs.impl.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.api.core.GenericDomain;

@Component
public class PageRules implements Serializable, GenericDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PageRule> pageRules;
	private String id;

	public List<PageRule> getPageRules() {
		return pageRules;
	}

	public void setPageRules(List<PageRule> pageRules) {
		this.pageRules = pageRules;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getObjectKey() {
		return null;
	}

	@Override
	public String getKey() {
		return null;
	}

}
