package app.cs.actions.contentplanning.pim;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.PIMInteractions;
import app.cs.impl.pim.PimRepository;

@RunWith(MockitoJUnitRunner.class)
public class PIMInteractionsUnitsTests {

	private PIMInteractions pimInteractions;
	@Mock
	private PimRepository pimRepository;

	private String id = "62";

	@Before
	public void setUp() {
		pimInteractions = new PIMInteractions(pimRepository);

	}
	
	

	@Test
	public void itShouldGetProductsFromPIMRepository() {

		String expectedProductList = "test products";
		// when
		when(pimRepository.getAssetsFor(id)).thenReturn(expectedProductList);
		String productList = pimInteractions.getProducts(id);

		// then
		verify(pimRepository).getAssetsFor(id);
		assertThat(productList).isEqualTo(expectedProductList);

	}
}
