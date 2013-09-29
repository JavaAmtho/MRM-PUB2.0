package app.cs.impl.model;

import java.util.HashMap;
import java.util.List;

public class PageRule {

	private List<RuleCondition> ruleConditions;
	private RuleResult ruleResult;
	private String RuleID;
	private HashMap<String, String> additionalInformation = new HashMap<String, String>();

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

	public String getRuleID() {
		return RuleID;
	}

	public void setRuleID(String ruleID) {
		RuleID = ruleID;
	}

	public HashMap<String, String> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(
			HashMap<String, String> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

}
