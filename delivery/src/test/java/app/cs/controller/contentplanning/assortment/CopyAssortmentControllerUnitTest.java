package app.cs.controller.contentplanning.assortment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.CopyAssortment;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CopyAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class CopyAssortmentControllerUnitTest {

	private CopyAssortmentController copyAssortmentController;

	@Mock
	private CopyAssortment interactor;

	@Mock
	private CopyAssortmentRequest copyAssortmentRequest;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		copyAssortmentController = new CopyAssortmentController(interactor,
				copyAssortmentRequest);
	}

	@Test
	public void itShouldCopyAnAssortment() {

		Assortment assortment = new Assortment();
		String newPath = "CP01,MP01,P01";
		String newName = "AS02";

		copyAssortmentRequest.setNewPath(newPath);
		copyAssortmentRequest.setAssortment(assortment);
		// when
		copyAssortmentController.copy(assortment, newPath, newName);

		// then
		verify(interactor).execute(copyAssortmentRequest);
	}

}
