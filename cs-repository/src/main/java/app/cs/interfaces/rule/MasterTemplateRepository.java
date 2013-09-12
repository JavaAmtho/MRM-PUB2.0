package app.cs.interfaces.rule;


public interface MasterTemplateRepository {

	public abstract String getMasterTemplates();

	String getMasterTemplates(String type);

}