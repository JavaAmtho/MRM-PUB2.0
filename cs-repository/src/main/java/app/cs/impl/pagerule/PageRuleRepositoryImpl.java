package app.cs.impl.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.model.PageRules;
import app.cs.interfaces.pagerule.IPageRuleRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

@Component
public class PageRuleRepositoryImpl implements IPageRuleRepository {

	private NoSqlRepository noSqlRepository;

	@Autowired
	public PageRuleRepositoryImpl(NoSqlRepository noSqlRepository) {
		this.noSqlRepository = noSqlRepository;
	}

	@Override
	public void savePageRules(PageRules pageRules) {
		String ret = noSqlRepository.save(pageRules);
		System.out.println(ret);
	}

	@Override
	public PageRules getPageRuleFor(String id) {
		PageRules pageRules = noSqlRepository.find(id, PageRules.class);
		System.out.println(noSqlRepository.find(id, PageRules.class)
				.getPageRules());
		return pageRules;
	}

}
