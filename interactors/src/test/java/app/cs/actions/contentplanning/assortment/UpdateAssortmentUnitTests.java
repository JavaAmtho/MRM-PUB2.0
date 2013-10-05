package app.cs.actions.contentplanning.assortment;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.UpdateAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAssortmentUnitTests {

	@Mock
	private AssortmentRepository assortmentRepository;

	private UpdateAssortment updateAssortment;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		updateAssortment = new UpdateAssortment(assortmentRepository);
	}


	@Test
	public void itShouldUpdateAnAssortment() {
		// given
		Assortment assortment = new Assortment();
		assortment.setID("1234");
		String path = "Mp01,P01";
		MultiDimensionalObject assortmentObject = new MultiDimensionalObject();
		assortmentObject.setPath(path);
		assortmentObject.setProducts(assortment.getProducts());
		assortmentObject.setId(assortment.getID());
		
		UpdateAssortmentRequest request = new UpdateAssortmentRequest();
		request.setAssortment(assortment);
		request.setPath(path);

		// when
		when(assortmentRepository.updateAssortment(assortmentObject)).thenReturn("updated 123");
		updateAssortment.execute(request);
				
		// then
		verify(assortmentRepository).updateAssortment(assortmentObject);

	}

}