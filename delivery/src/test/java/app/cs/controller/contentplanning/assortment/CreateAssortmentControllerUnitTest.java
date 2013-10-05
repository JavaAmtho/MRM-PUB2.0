package app.cs.controller.contentplanning.assortment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.CreateAssortment;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CreateAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class CreateAssortmentControllerUnitTest {

	private CreateAssortmentController createAssortmentController;

	@Mock
	private CreateAssortment interactor;
	
	@Mock
	private CreateAssortmentRequest createAssortmentRequest;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		createAssortmentController = new CreateAssortmentController(interactor , createAssortmentRequest);
	}

	@Test
	public void itShouldCreateAnAssortment() {
		
		Assortment assortment = new Assortment();
		String path = "CP01,MP01,P01";
		String name = "AS01";

		createAssortmentRequest.setPath(path);
		createAssortmentRequest.setAssortment(assortment);
		// when
		createAssortmentController.create(assortment, path, name);

		// then
		verify(interactor).execute(createAssortmentRequest);
	}
	
	
}
