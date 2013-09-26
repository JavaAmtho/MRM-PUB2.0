package app.cs.controller.publicationstructuring.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.CreateChapterRequest;

/**
 * The Class ChapterController. TODO. com.cs.business.ifacadeservices controller
 * -> common facade ->>>(|) ->i***interface call ichapter ->Impl idimension
 * ->Impl
 */
@Controller
public class CreateChapterController {

	/** The Constant CREATE. */
	private static final String CREATECHAPTER = "/chapter/create/{type}/name/{name}/path/{path}/folder/{folder}";

	/** The chapter service. */
	private Interactor createChapter;

	private CreateChapterRequest createChapterRequestModel;

	/**
	 * Instantiates a new chapter controller.
	 * 
	 * @param chapterService
	 *            the chapter service
	 * @param factory
	 *            the factory
	 */
	@Autowired
	public CreateChapterController(Interactor createChapter,
			CreateChapterRequest createChapterRequestModel) {
		this.createChapter = createChapter;
		this.createChapterRequestModel = createChapterRequestModel;

	}

	/**
	 * Creates the.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @return the string
	 */
	@RequestMapping(value = { CREATECHAPTER})
	public @ResponseBody
	String execute(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") boolean isFolder) {


		createChapterRequestModel.setFolder(isFolder);
		createChapterRequestModel.setName(name);
		createChapterRequestModel.setPath(path);
		createChapterRequestModel.setType(type);
		createChapter.execute(createChapterRequestModel);
		return name;

	}

}
