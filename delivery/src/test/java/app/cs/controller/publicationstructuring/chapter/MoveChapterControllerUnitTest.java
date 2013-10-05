package app.cs.controller.publicationstructuring.chapter;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationstructuring.chapter.MoveChapter;
import app.cs.model.request.MoveChapterRequest;

@RunWith(MockitoJUnitRunner.class)
public class MoveChapterControllerUnitTest {

	private MoveChapterController moveChapterController;

	@Mock
	private MoveChapter moveChapter;

	@Mock
	private MoveChapterRequest moveChapterRequest;

	@Before
	public void setUp() {
		moveChapterController = new MoveChapterController(moveChapter,
				moveChapterRequest);

	}

	@Test
	public void itShouldChapterServiceToMoveAnObjectToGivenLocation() {

		// given
		String newPath = "A-B-D";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;


		// when
		moveChapterController.execute(type, name, path, isFolder, newPath);

		// then
		verify(moveChapter).execute(moveChapterRequest);
	}
}
