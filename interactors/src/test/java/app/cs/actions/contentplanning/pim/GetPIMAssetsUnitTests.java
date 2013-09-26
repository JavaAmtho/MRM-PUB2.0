package app.cs.actions.contentplanning.pim;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.pim.PimRepository;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class GetPIMAssetsUnitTests{

	private GetPIMAssets getPIMAssets;

	@Mock
	private PimRepository pimRepository;

	private String id = "62";

	@Before
	public void setUp() {
		getPIMAssets = new GetPIMAssets(pimRepository);
	}

	@Test
	public void itShouldGetAssetsFromPIMRepository() {

		String expectedAssetList = "test assets";
		
		StringRequest stringRequest = new StringRequest(id);
		// when
		when(pimRepository.getAssetsFor(id)).thenReturn(expectedAssetList);
		ResponseModel assetList = getPIMAssets.execute(stringRequest);
		
		StringResponse response = (StringResponse) assetList;
		

		// then
		verify(pimRepository).getAssetsFor(id);
		assertThat(response.getResponseString()).isEqualTo(expectedAssetList);

	}
}