package app.cs.interfaces.pagegeneration;

public interface IPageGenerationRepository {

	public String createAndPlanWBD(String ruleID, String logicalPageID,
			String publicationID);

}
