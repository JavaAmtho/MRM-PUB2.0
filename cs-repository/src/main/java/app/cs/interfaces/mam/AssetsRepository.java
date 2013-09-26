package app.cs.interfaces.mam;

public interface AssetsRepository {

	public abstract String getAssetsFor(String id);

	public abstract String getSearchResults(String searchQuery);

}