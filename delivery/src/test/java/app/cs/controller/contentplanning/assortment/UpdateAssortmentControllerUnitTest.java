package app.cs.controller.contentplanning.assortment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.UpdateAssortment;
import app.cs.impl.model.Assortment;
import app.cs.model.request.UpdateAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAssortmentControllerUnitTest {

	private UpdateAssortmentController updateAssortmentController;

	@Mock
	private UpdateAssortment interactor;

	@Mock
	private Assortment assortment;

	@Mock
	private UpdateAssortmentRequest updateAssortmentRequest;

	@Before
	public void setUp() {
		updateAssortmentController = new UpdateAssortmentController(interactor,
				updateAssortmentRequest);
	}

	@Test
	public void itShouldUpdateAnAssortment() {

		Assortment assortment = new Assortment();
		String path = "CP01,MP03";
		String name = "ass1";
		updateAssortmentRequest.setAssortment(assortment);

		// when
		updateAssortmentController.execute(assortment, name, path);

		// then
		verify(interactor).execute(updateAssortmentRequest);
	}

}
