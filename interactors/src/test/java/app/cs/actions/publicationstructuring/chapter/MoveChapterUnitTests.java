package app.cs.actions.publicationstructuring.chapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.MoveChapterRequest;

@RunWith(MockitoJUnitRunner.class)
public class MoveChapterUnitTests {

	private MoveChapter moveChapter;

	@Mock
	private ChapterRepository chapterRepository;

	MoveChapterRequest moveChapterRequest;

	@Mock
	private DomainFactory factory;

	@Before
	public void setUp() {
		moveChapter = new MoveChapter(chapterRepository);

	}

	@Test
	public void itShouldMoveAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";

		String type = "Campaign";
		String name = "CP01";
		String path = "Mp01,P01";
		boolean isFolder = true;
		String newPath = "Mp02,p02";
		MultiDimensionalObject object = new MultiDimensionalObject(name, type,
				path, isFolder);

		moveChapterRequest = new MoveChapterRequest(type, name, path, isFolder,
				newPath);

		// when
		when(chapterRepository.getDomain("MultiDimensionalObject")).thenReturn(
				object);
		moveChapter.execute(moveChapterRequest);

		// then
		verify(chapterRepository).move(object, newPath);
		assertEquals(isFolder,true);

	}

}
