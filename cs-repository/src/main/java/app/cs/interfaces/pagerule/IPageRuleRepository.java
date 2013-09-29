package app.cs.interfaces.pagerule;

import app.cs.impl.model.PageRule;
import app.cs.impl.model.PageRules;

public interface IPageRuleRepository {

	public void savePageRules(PageRules pageRules);

	public abstract PageRules getPageRulesFor(String logicalPageID);

	public abstract PageRule getPageRuleFor(PageRules pageRules, String ruleID);
}
