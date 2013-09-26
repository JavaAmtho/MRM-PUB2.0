package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.pim.PimRepository;

@Component
public class PIMInteractions {

	private PimRepository pimRepository;

	@Autowired
	public PIMInteractions(PimRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public String getProducts(String id) {
		return pimRepository.getAssetsFor(id);

	}

}
