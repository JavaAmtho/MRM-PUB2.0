package app.cs.actions.publicationplanning.perspective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.inmemory.IInMemoryViewStructure;
import app.cs.interfaces.slicingdicing.ITreeBuilder;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.TreeResponse;

/**
 * The Class DimensionService.
 */
@Component
public class SwitchPerspective implements Interactor{

	/** The tree builder. */
	private ITreeBuilder treeBuilder;

	/** The ViewStructure cache. */
	private IInMemoryViewStructure cache;

	/** The Constant KEY. */
	private static final String KEY = "view";

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public SwitchPerspective(ITreeBuilder treeBuilder,
			IInMemoryViewStructure cache) {

		this.treeBuilder = treeBuilder;
		this.cache = cache;

	}

	public ResponseModel execute(RequestModel model) {
		StringRequest request = (StringRequest) model;
		setCurrentViewStructure(request.getStringRequest());
		return new TreeResponse(treeBuilder.buildTree(request
				.getStringRequest()));

	}

	public void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

}
