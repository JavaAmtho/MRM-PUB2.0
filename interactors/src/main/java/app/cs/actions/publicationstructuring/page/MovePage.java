package app.cs.actions.publicationstructuring.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.chapter.IChapterRepository;
import app.cs.model.request.MovePageRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

/**
 * The Class ChapterService.
 */
@Component
public class MovePage implements Interactor {

	/** The contentobject. */
	private final String CONTENTOBJECT = "MultiDimensionalObject";

	/** The chapter repository. */
	private IChapterRepository chapterRepository;

	/**
	 * Instantiates a new chapter service.
	 * 
	 * @param chapterRepository
	 *            the chapter repository
	 * @param factory
	 */
	@Autowired
	public MovePage(IChapterRepository chapterRepository) {
		// TODO Auto-generated constructor stub
		this.chapterRepository = chapterRepository;
	}

	public ResponseModel execute(RequestModel model) {
		MovePageRequest request = (MovePageRequest) model;

		MultiDimensionalObject chapter = chapterRepository
				.getDomain(CONTENTOBJECT);
		setChapterAtrributes(chapter, request.getType(), request.getName(),
				request.getPath(), request.isFolder());
		chapterRepository.move(chapter, request.getNewPath());
		return new EmptyResponse();

	}

	/**
	 * Sets the chapter atrributes.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */
	private void setChapterAtrributes(MultiDimensionalObject chapter,
			String type, String name, String path, boolean isFolder) {
		chapter.setId(name);
		chapter.setTitle(name);
		chapter.setIsFolder(isFolder);
		chapter.setPath(path);
		chapter.setName(name);
		chapter.setType(type);

	}

}
