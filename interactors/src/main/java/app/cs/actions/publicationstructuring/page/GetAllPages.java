package app.cs.actions.publicationstructuring.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.chapter.ChapterRepository;
import app.cs.model.request.GetAllPagesRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.TreeResponse;

@Component
public class GetAllPages implements Interactor {

	private ChapterRepository chapterRepository;

	@Autowired
	public GetAllPages(ChapterRepository repository) {
		this.chapterRepository = repository;
	}

	@Override
	public ResponseModel execute(RequestModel requestMdel) {
		GetAllPagesRequest getAllPagesRequest = (GetAllPagesRequest) requestMdel;
		return new TreeResponse(
				chapterRepository.getAllPages(getAllPagesRequest
						.getPublicationId()));
	}

}
