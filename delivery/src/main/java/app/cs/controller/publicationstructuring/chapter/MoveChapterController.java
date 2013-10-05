package app.cs.controller.publicationstructuring.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.MoveChapterRequest;

/**
 * The Class ChapterController. TODO. com.cs.business.ifacadeservices controller
 * -> common facade ->>>(|) ->i***interface call ichapter ->Impl idimension
 * ->Impl
 */
@Controller
public class MoveChapterController {

	/** The Constant MOVE. */
	private static final String MOVECHAPTER = "/chapter/move/{type}/name/{name}/path/{path}/folder/{folder}/newpath/{newpath}";

	private Interactor moveChapter;
	private MoveChapterRequest moveChapterRequest;

	/**
	 * Instantiates a new chapter controller.
	 * 
	 * @param chapterService
	 *            the chapter service
	 * @param factory
	 *            the factory
	 */
	@Autowired
	public MoveChapterController(Interactor moveChapter,
			MoveChapterRequest moveChapterRequest) {
		this.moveChapter = moveChapter;
		this.moveChapterRequest = moveChapterRequest;

	}

	/**
	 * Move.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @param newPath
	 *            the new path
	 */
	@RequestMapping(value = { MOVECHAPTER })
	public @ResponseBody
	String execute(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") boolean isFolder,
			@PathVariable("newpath") String newpath) {

		moveChapterRequest.setType(type);
		moveChapterRequest.setName(name);
		moveChapterRequest.setPath(path);
		moveChapterRequest.setFolder(isFolder);
		moveChapterRequest.setNewPath(newpath);

		System.out.println("==>" + type + name + path + isFolder + newpath);
		moveChapter.execute(moveChapterRequest);
		return name;

	}
}
