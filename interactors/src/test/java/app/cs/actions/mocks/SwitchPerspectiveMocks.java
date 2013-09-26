package app.cs.actions.mocks;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
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
public class SwitchPerspectiveMocks implements Interactor {

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
	public SwitchPerspectiveMocks(ITreeBuilder treeBuilder,
			IInMemoryViewStructure cache) {

		this.treeBuilder = treeBuilder;
		this.cache = cache;

	}

	public ResponseModel execute(RequestModel model) {
		
		StringRequest request = (StringRequest) model;
		String structure = request.getStringRequest();
		
		try {
			if (structure.equals("MasterPublication-Campaign-Publication")) {

				List<MultiDimensionalObject> dimensionalObjects = new ObjectMapper()
						.readValue(
								"[\n    {\n        \"id\": \"MP01\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP01\",\n        \"title\": \"MP01\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP01\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP01\",\n                \"name\": \"CP01\",\n                \"title\": \"CP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P01\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP01,CP01\",\n                        \"name\": \"P01\",\n                        \"title\": \"P01\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter02\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"CP01,MP01,P01\",\n                                \"name\": \"Chapter02\",\n                                \"title\": \"Chapter02\",\n                                \"isFolder\": true,\n                                \"children\": [\n                                    {\n                                        \"id\": \"Page03\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page03\",\n                                        \"title\": \"Page03\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null,\n                                        \"groupId\": null\n                                    },\n                                    {\n                                        \"id\": \"Page02\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page02\",\n                                        \"title\": \"Page02\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    }\n                                ],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n      \n        \"groupId\": [\n            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n        ]\n    },\n    {\n        \"id\": \"MP02\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP02\",\n        \"title\": \"MP02\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP01\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP02\",\n                \"name\": \"CP01\",\n                \"title\": \"CP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P02\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP02,CP01\",\n                        \"name\": \"P02\",\n                        \"title\": \"P02\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter01\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"MP02,CP01,P02\",\n                                \"name\": \"Chapter01\",\n                                \"title\": \"Chapter01\",\n                                \"isFolder\": true,\n                                \"children\": [],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null\n                    }\n                ],\n                \"groupId\": [\n                    \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n        ]\n    },\n    {\n        \"id\": \"MP03\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP03\",\n        \"title\": \"MP03\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP02\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP03\",\n                \"name\": \"CP02\",\n                \"title\": \"CP02\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P03\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP03,CP02\",\n                        \"name\": \"P03\",\n                        \"title\": \"P03\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Page01\",\n                                \"type\": \"Page\",\n                                \"path\": \"CP02,MP03,P03\",\n                                \"name\": \"Page01\",\n                                \"title\": \"Page01\",\n                                \"isFolder\": false,\n                                \"children\": null,\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"7c8e3991-e0f3-4e4d-8489-fb73cdde7d4a\",\n                    \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n        ]\n    }\n]",
								new TypeReference<List<MultiDimensionalObject>>() {
								});
				return new TreeResponse(dimensionalObjects);
			} else {
				List<MultiDimensionalObject> dimensionalObjects = new ObjectMapper()
						.readValue(
								"[\n    {\n        \"id\": \"CP01\",\n        \"type\": \"Campaign\",\n        \"path\": \"-1\",\n        \"name\": \"CP01\",\n        \"title\": \"CP01\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"MP01\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP01\",\n                \"name\": \"MP01\",\n                \"title\": \"MP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P01\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP01,MP01\",\n                        \"name\": \"P01\",\n                        \"title\": \"P01\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter02\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"CP01,MP01,P01\",\n                                \"name\": \"Chapter02\",\n                                \"title\": \"Chapter02\",\n                                \"isFolder\": true,\n                                \"children\": [\n                                    {\n                                        \"id\": \"Page03\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page03\",\n                                        \"title\": \"Page03\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    },\n                                    {\n                                        \"id\": \"Page02\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page02\",\n                                        \"title\": \"Page02\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    }\n                                ],\n                                \"groupId\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n                ]\n            },\n            {\n                \"id\": \"MP02\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP01\",\n                \"name\": \"MP02\",\n                \"title\": \"MP02\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P02\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP01,MP02\",\n                        \"name\": \"P02\",\n                        \"title\": \"P02\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter01\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"MP02,CP01,P02\",\n                                \"name\": \"Chapter01\",\n                                \"title\": \"Chapter01\",\n                                \"isFolder\": true,\n                                \"children\": [],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n        ]\n    },\n    {\n        \"id\": \"CP02\",\n        \"type\": \"Campaign\",\n        \"path\": \"-1\",\n        \"name\": \"CP02\",\n        \"title\": \"CP02\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"MP03\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP02\",\n                \"name\": \"MP03\",\n                \"title\": \"MP03\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P03\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP02,MP03\",\n                        \"name\": \"P03\",\n                        \"title\": \"P03\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Page01\",\n                                \"type\": \"Page\",\n                                \"path\": \"CP02,MP03,P03\",\n                                \"name\": \"Page01\",\n                                \"title\": \"Page01\",\n                                \"isFolder\": false,\n                                \"children\": null,\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"7c8e3991-e0f3-4e4d-8489-fb73cdde7d4a\",\n            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n        ]\n    }\n]",
								new TypeReference<List<MultiDimensionalObject>>() {
								});
				return new TreeResponse(dimensionalObjects);

			}
		} catch (Exception e) {
			return null;
		}
}

	public void setCurrentViewStructure(String currentViewStructure) {

	}

}
