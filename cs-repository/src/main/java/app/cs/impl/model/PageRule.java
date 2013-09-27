package app.cs.impl.model;

import java.util.List;

public class PageRule {

	private List<RuleCondition> ruleConditions;
	private RuleResult ruleResult;
	
	
	public List<RuleCondition> getRuleConditions() {
		return ruleConditions;
	}
	
	public void setRuleConditions(List<RuleCondition> ruleConditions) {
		this.ruleConditions = ruleConditions;
	}
	
	public RuleResult getRuleResult() {
		return ruleResult;
	}
	
	public void setRuleResult(RuleResult ruleResult) {
		this.ruleResult = ruleResult;
	}
	
	
}
