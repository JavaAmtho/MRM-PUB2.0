package app.cs.actions.contentplanning.assortment;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.CopyAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class CopyAssortmentUnitTests {

	@Mock
	private AssortmentRepository assortmentRepository;

	private CopyAssortment copyAssortment;

	@Before
	public void setUp() {
		copyAssortment = new CopyAssortment(assortmentRepository);
	}

	@Test
	public void itShouldCopyAnAssortment() {

		// given
		Assortment assortment = new Assortment();
		MultiDimensionalObject assortmentObject = new MultiDimensionalObject();

		String type = "assortment";
		String name = "AS01";
		String path = "Mp01,P01";
		String newPath = "Mp02,p02";

		assortmentObject.setId(UUID.randomUUID().toString());
		assortmentObject.setPath(path);
		CopyAssortmentRequest request = new CopyAssortmentRequest();
		request.setAssortment(assortment);
		request.setNewPath(newPath);

		// when
		when(assortmentRepository.getDomain("MultiDimensionalObject"))
				.thenReturn(assortmentObject);
		copyAssortment.execute(request);

		// then
		verify(assortmentRepository).copy(assortmentObject);

	}
}