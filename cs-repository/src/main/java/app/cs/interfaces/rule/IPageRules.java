package app.cs.interfaces.rule;

import java.util.List;

import app.cs.impl.model.PageRule;

public interface IPageRules {

	public List<PageRule> getPageRules();
	
	public void setPageRules(List<PageRule> pageRules);
	
	public int getId();
	
	public void setId(int id);
	
}
