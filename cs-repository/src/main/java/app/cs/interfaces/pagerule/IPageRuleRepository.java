package app.cs.interfaces.pagerule;

import app.cs.impl.model.PageRules;

public interface IPageRuleRepository {

	public void savePageRules(PageRules pageRules);
	public abstract PageRules getPageRuleFor(String id);

}
