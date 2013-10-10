package app.cs.interfaces.pagerule;
/*
 * TODO: Make it a model, or deprecate
 */
public class PageRuleCondition {
	
	public String variable;
	public String operator;
	public String value;
	
	public PageRuleCondition(String variable, String operator, String value) {
		super();
		this.variable = variable;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public String toString() {
		return "PageRuleCondition [variable=" + variable + ", operator="
				+ operator + ", value=" + value + "]";
	}
	
	

}
