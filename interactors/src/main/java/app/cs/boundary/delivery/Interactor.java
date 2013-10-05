package app.cs.boundary.delivery;

import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;

public interface Interactor {

	public ResponseModel execute(RequestModel requestMdel);

}
