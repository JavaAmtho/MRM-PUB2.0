package app.cs.model.request;

import java.util.List;

import app.cs.impl.model.RuleCondition;

public class RuleConditionRequest implements RequestModel {

	private List<RuleCondition> ruleConditions;
	private String id;
	
	public List<RuleCondition> getRuleConditions() {
		return ruleConditions;
	}
	
	public void setRuleConditions(List<RuleCondition> ruleConditions) {
		this.ruleConditions = ruleConditions;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
}
