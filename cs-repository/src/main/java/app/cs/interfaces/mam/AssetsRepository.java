package app.cs.interfaces.mam;
/*
 * TODO: Make it abstract class, Possible subclasses: Pim,Mam,MasterTemplate,Pagegeneration
 */
public interface AssetsRepository {

	public abstract String getAssetsFor(String id);

	public abstract String getSearchResults(String searchQuery);

}