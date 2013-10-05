package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.mam.AssetsRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class MAMSearchMock implements Interactor{

	private AssetsRepository mamRepository;

	@Autowired
	public MAMSearchMock(AssetsRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public ResponseModel execute(RequestModel model) {
		
		return new StringResponse("Media 2345, Media 5678, Media 456, Media 47, Media 4567, Media 1354");

	}

}