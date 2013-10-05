package app.cs.interfaces.pagerule;

import app.cs.impl.model.PageRules;

public interface PageRuleGetRepository {
	public abstract PageRules getPageRuleFor(String id);

}