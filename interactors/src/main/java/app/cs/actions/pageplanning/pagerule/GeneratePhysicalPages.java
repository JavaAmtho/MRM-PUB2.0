package app.cs.actions.pageplanning.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.RuleConditions;
import app.cs.interfaces.pagerule.IRuleConditionsRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.RuleConditionRequest;
import app.cs.model.response.ResponseModel;

@Component
public class GeneratePhysicalPages implements Interactor {

	private IRuleConditionsRepository ruleConditionsRepository;
	private RuleConditions ruleConditions;

	@Autowired
	public GeneratePhysicalPages(
			IRuleConditionsRepository ruleConditionsRepository,
			RuleConditions ruleConditions) {
		this.ruleConditionsRepository = ruleConditionsRepository;
		this.ruleConditions = ruleConditions;
	}

	@Override
	public ResponseModel execute(RequestModel requestModel) {
		RuleConditionRequest ruleConditionRequest = (RuleConditionRequest) requestModel;

		ruleConditions.setId(ruleConditionRequest.getId());
		ruleConditions.setRuleConditions(ruleConditionRequest
				.getRuleConditions());
		ruleConditionsRepository.generatePhysicalPages(ruleConditions);
		return null;
	}

}
