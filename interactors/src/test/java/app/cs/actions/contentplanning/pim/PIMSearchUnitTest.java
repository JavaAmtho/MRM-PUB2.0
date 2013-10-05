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
public class PIMSearchUnitTest {

	private PimSearch pimSearch;

	@Mock
	private PimRepository pimRepository;
	

	@Before
	public void setUp() {
		pimSearch = new PimSearch(pimRepository);
	}

	@Test
	public void itShouldSearchAssetsForGivenKey() {

		String expectedSearch = "Search Result";
		String id = "PQR";
		
		StringRequest stringRequest= new StringRequest(id);
		// when
		when(pimRepository.getSearchResults(id)).thenReturn(expectedSearch);
		ResponseModel searchResult = pimSearch.execute(stringRequest);

		StringResponse response = (StringResponse) searchResult;
	
		// then
		verify(pimRepository).getSearchResults(id);
		assertThat(response.getResponseString()).isEqualTo(expectedSearch);

	}
}