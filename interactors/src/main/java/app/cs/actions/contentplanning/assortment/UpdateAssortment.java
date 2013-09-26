package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.UpdateAssortmentRequest;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class UpdateAssortment implements Interactor {

	private IAssortmentRepository assortmentRepository;

	@Autowired
	public UpdateAssortment(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	@Override
	public ResponseModel execute(RequestModel requestModel) {

		UpdateAssortmentRequest updateAssortmentRequest = (UpdateAssortmentRequest) requestModel;
		MultiDimensionalObject assortmentObject = new MultiDimensionalObject();
		Assortment assortment = updateAssortmentRequest.getAssortment();
		assortmentObject.setPath(updateAssortmentRequest.getPath());

		assortmentObject.setId(assortment.getID());
		System.out.println(assortment.getProducts());
		assortmentObject.setProducts(assortment.getProducts());
		assortmentObject.setName(updateAssortmentRequest.getName());

		assortmentRepository.updateAssortment(assortmentObject);
		return new EmptyResponse();
	}

}
