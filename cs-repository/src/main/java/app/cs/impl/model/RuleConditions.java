package app.cs.impl.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.api.core.GenericDomain;

@Component
public class RuleConditions implements Serializable, GenericDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public String getObjectKey() {
		return null;
	}

	@Override
	public String getKey() {
		return null;
	}

}
