package app.cs.impl.chapter;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;

import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private Finder finder;

	@Mock
	private DomainFactory factory;

	MultiDimensionalObject publication;

	MultiDimensionalObject test;

	@Before
	public void setUp() {
		repository = new ChapterRepository(noSqlTemplateForMongo, factory,
				finder);
		publication = new MultiDimensionalObject("Test", "publication",
				"CP01,MP01,PG01", true);
		test = new MultiDimensionalObject("test01", "chapter",
				"CP01,MP01,PG01,P01", true);
		test.addchild(new MultiDimensionalObject("test03", "test",
				"CP01,MP01,PG01,P01", true));
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test",
				"CP01,MP01,PG01,P01,test01", true));

	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "CP01,MP01,PG01,P01,test03", true);
		String result = "success";
		// when
		when(noSqlTemplateForMongo.save(publication)).thenReturn(result);
		when(finder.getPublicationId(chapter.getPath())).thenReturn("P01");
		when(
				noSqlTemplateForMongo.getObjectByKey("P01",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		String actualResult = repository.save(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("P01",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);

		assertThat(actualResult).isEqualTo(result);
	}

	@Test
	public void itShouldDeleteGivenNode() {
		// given

		String result = "result";
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "CP01,MP01,PG01,P01,test01", true);
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(finder.getPublicationId(chapter.getPath())).thenReturn("P01");
		when(
				noSqlTemplateForMongo.getObjectByKey("P01",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		// when
		repository.delete(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("P01",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);

	}

	@Test
	public void itShouldMoveChapterFromOneLocationToOther() {
		String result = "result";
		String newPath = "CP01,MP01,PG01,P01,test02";
		MultiDimensionalObject chapter = new MultiDimensionalObject("test01",
				"test", "CP01,MP01,PG01,P01,test01", true);
		// when
		when(finder.getPublicationId(chapter.getPath())).thenReturn("P01");
		when(finder.getPublicationId(newPath)).thenReturn("P01");
		when(
				noSqlTemplateForMongo.getObjectByKey("P01",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(newPath)).thenReturn("test");
		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		when(finder.find(publication, chapter.getId())).thenReturn(test);
		when(finder.find(publication, "test")).thenReturn(test);
		repository.move(chapter, newPath);

		// then
		verify(noSqlTemplateForMongo, times(2)).save(publication);

	}
}
