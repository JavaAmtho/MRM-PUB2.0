package app.cs.actions.ruleplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.rule.MasterTemplateRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetMasterTemplate implements Interactor {

	private MasterTemplateRepository masterTemplateRepository;

	@Autowired
	public GetMasterTemplate(MasterTemplateRepository masterTemplateRepository) {
		this.masterTemplateRepository = masterTemplateRepository;
	}

	public ResponseModel execute(RequestModel model) {
		return new StringResponse(masterTemplateRepository.getMasterTemplates("cache"));
	}

}
