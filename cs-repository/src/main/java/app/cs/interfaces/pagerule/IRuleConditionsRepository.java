package app.cs.interfaces.pagerule;

import app.cs.impl.model.RuleConditions;


public interface IRuleConditionsRepository {
	
	public void generatePhysicalPages(RuleConditions ruleConditions);

}
