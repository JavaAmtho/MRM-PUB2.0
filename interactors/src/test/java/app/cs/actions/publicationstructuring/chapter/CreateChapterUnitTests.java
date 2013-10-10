package app.cs.actions.publicationstructuring.chapter;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterAndPageRepository;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.CreateChapterRequest;
import app.cs.model.response.ResponseModel;

@RunWith(MockitoJUnitRunner.class)
public class CreateChapterUnitTests {

	private CreateChapter createChapter;

	@Mock
	private ChapterAndPageRepository chapterRepository;

	@Mock
	private DomainFactory factory;

	@Before
	public void setUp() {
		createChapter = new CreateChapter(chapterRepository);

	}

	@Test
	public void itShouldCreateAChapter() {

		// given

		String result = "success";
		String name = "test";
		String path = "A,B";
		String type = "chapter";
		boolean isFolder = true;
		// when
		MultiDimensionalObject object = new MultiDimensionalObject();
		when(chapterRepository.save(object)).thenReturn(result);
		when(chapterRepository.getDomain("MultiDimensionalObject")).thenReturn(
				object);
		ResponseModel actualResult = createChapter
				.execute(new CreateChapterRequest(type, name, path, isFolder));

		// then
		verify(chapterRepository).getDomain("MultiDimensionalObject");
		verify(chapterRepository).save(object);
		assertThat(actualResult).isEqualTo(actualResult);
		assertEquals(isFolder,true);

	}
	

}
