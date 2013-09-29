package app.cs.impl.pagerule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.model.PageRule;
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
		noSqlRepository.save(pageRules);
	}

	@Override
	public PageRules getPageRulesFor(String logicalPageID) {
		PageRules pageRules = noSqlRepository.find(logicalPageID,
				PageRules.class);
		return pageRules;
	}

	@Override
	public PageRule getPageRuleFor(PageRules pageRules, String ruleID) {
		List<PageRule> listOfPageRules = pageRules.getPageRules();
		for (PageRule pageRule : listOfPageRules) {
			if (pageRule.getRuleID().equals(ruleID)) {
				return pageRule;
			}
		}
		return null;
	}

}
