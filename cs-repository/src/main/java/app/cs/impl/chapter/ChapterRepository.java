package app.cs.impl.chapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.chapter.IChapterRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class ChapterRepository. TODO create separate Object for Chapter & Page,
 * Dont use ContentObject(DimensionObject) TODO with separate interface****
 */
@Component
public class ChapterRepository implements IChapterRepository {
	/** The nosql template for mongo. */
	private NoSqlRepository mongoRepository;

	/** The finder. */
	private Finder finder;

	private DomainFactory factory;

	/**
	 * Instantiates a new chapter repository.
	 * 
	 * @param nosqlTemplateForMongo
	 *            the nosql template for mongo
	 * @param cache
	 *            the cache
	 */
	@Autowired
	public ChapterRepository(NoSqlRepository noSqlRepository,
			DomainFactory factory, Finder finder) {
		this.mongoRepository = noSqlRepository;
		this.factory = factory;
		this.finder = finder;

	}

	@Override
	public String save(MultiDimensionalObject chapter) {

		MultiDimensionalObject publication = getParentPublication(chapter
				.getPath());
		return addChapterToPublication(publication, chapter);

	}

	/**
	 * Adds the chapter to publication.
	 * 
	 * @param publication
	 *            the publication
	 * @param chapter
	 *            the chapter
	 */
	private String addChapterToPublication(MultiDimensionalObject publication,
			MultiDimensionalObject chapter) {
		MultiDimensionalObject parent;
		parent = finder
				.find(publication, finder.getParentId(chapter.getPath()));
		parent.addchild(chapter);
		return saveToRepository(publication);

	}

	/**
	 * Save given publication to mongoDb database..
	 * 
	 * @param publication
	 *            the publication
	 */
	private String saveToRepository(MultiDimensionalObject publication) {
		return mongoRepository.save(publication);
	}

	@Override
	public MultiDimensionalObject getDomain(String type) {

		return factory.getDomainObject(type);
	}

	@Override
	public String delete(MultiDimensionalObject chapter) {
		MultiDimensionalObject parentPublication = getParentPublication(chapter
				.getPath());
		MultiDimensionalObject parent = finder.find(parentPublication,
				finder.getParentId(chapter.getPath()));
		parent.removeChild(chapter);
		saveToRepository(parentPublication);
		return chapter.getId();
	}

	public MultiDimensionalObject findMultiDimensionalObjectByID(
			MultiDimensionalObject publication, String assortmentID) {
		return finder.find(publication, assortmentID);
	}

	@Override
	public void move(MultiDimensionalObject chapter, String newPath) {
		MultiDimensionalObject parentPublication = getParentPublication(chapter
				.getPath());
		MultiDimensionalObject chapterForNewLocation = finder.find(
				parentPublication, chapter.getId());
		chapterForNewLocation.setPath(newPath);
		save(chapterForNewLocation);
		delete(chapter);
	}

	public MultiDimensionalObject getParentPublication(String path) {
		return mongoRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}

	public MultiDimensionalObject getParentPublicationByID(String id) {
		return mongoRepository.getObjectByKey(id, MultiDimensionalObject.class);
	}

	public List<MultiDimensionalObject> getAllPages(String publicationId) {

		List<MultiDimensionalObject> pages = new ArrayList<MultiDimensionalObject>();
		finder.setAllPagesToEmpty();
		finder.find(mongoRepository.getObjectByKey(publicationId,
				MultiDimensionalObject.class), "");
		pages.addAll(finder.getAllPages());
		return pages;

	}
}
