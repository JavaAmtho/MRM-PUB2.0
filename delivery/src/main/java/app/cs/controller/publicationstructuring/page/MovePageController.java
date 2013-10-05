package app.cs.controller.publicationstructuring.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.MovePageRequest;

/**
 * The Class ChapterController. TODO. com.cs.business.ifacadeservices controller
 * -> common facade ->>>(|) ->i***interface call ichapter ->Impl idimension
 * ->Impl
 */
@Controller
public class MovePageController {

	/** The Constant MOVE. */
	private static final String MOVEPAGE = "/page/move/{type}/name/{name}/path/{path}/folder/{folder}/newpath/{newpath}";

	private Interactor movePage;
	private MovePageRequest movePageRequest;

	/**
	 * Instantiates a new page controller.
	 * 
	 * @param pageService
	 *            the page service
	 * @param factory
	 *            the factory
	 */
	@Autowired
	public MovePageController(Interactor movePage,
			MovePageRequest movePageRequest) {
		this.movePage = movePage;
		this.movePageRequest = movePageRequest;

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
	@RequestMapping(value = { MOVEPAGE })
	public @ResponseBody
	String execute(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") boolean isFolder,
			@PathVariable("newpath") String newpath) {

		movePageRequest.setType(type);
		movePageRequest.setName(name);
		movePageRequest.setPath(path);
		movePageRequest.setFolder(isFolder);
		movePageRequest.setNewPath(newpath);

		System.out.println("==>" + type + name + path + isFolder + newpath);
		movePage.execute(movePageRequest);
		return name;

	}
}
