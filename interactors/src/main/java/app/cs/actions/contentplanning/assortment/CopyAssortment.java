package app.cs.actions.contentplanning.assortment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CopyAssortment implements Interactor {

	private IAssortmentRepository assortmentRepository;

	private String type = "Assortment";

	@Autowired
	public CopyAssortment(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {
		CopyAssortmentRequest copyAssortmentRequest = (CopyAssortmentRequest) request;
		MultiDimensionalObject assortmentObject = assortmentRepository
				.getDomain("MultiDimensionalObject");

		Assortment assortment = copyAssortmentRequest.getAssortment();

		assortmentObject.setId(UUID.randomUUID().toString());
		assortmentObject.setName(copyAssortmentRequest.getNewName());
		assortmentObject.setIsFolder(false);
		assortmentObject.setType(type);
		assortmentObject.setTitle(copyAssortmentRequest.getNewName());
		assortmentObject.setPath(copyAssortmentRequest.getNewPath());
		assortmentObject.setProducts(assortment.getProducts());

		System.out.println("Interactor");
		assortmentRepository.copy(assortmentObject);
		return new EmptyResponse();

	}
}
