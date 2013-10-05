package app.cs.actions.contentplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.mam.AssetsRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetPIMAssets implements Interactor {

	private AssetsRepository pimRepository;

	@Autowired
	public GetPIMAssets(AssetsRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public ResponseModel execute(RequestModel model) {
		StringRequest request = (StringRequest) model;
		return new StringResponse(pimRepository.getAssetsFor(request
				.getStringRequest()));

	}

}
