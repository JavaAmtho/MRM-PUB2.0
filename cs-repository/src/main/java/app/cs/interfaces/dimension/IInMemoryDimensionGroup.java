package app.cs.interfaces.dimension;

import java.util.List;

import app.cs.impl.model.MultiDimensionalObject;


public interface IInMemoryDimensionGroup {

	/**
	 * Gets the dimension group id for the given path.
	 *
	 * @param path the path
	 * @return the dimension group id for
	 */
	public abstract String getDimensionGroupIdFor(String path);

	/**
	 * Update cache for the given group taking the given dimension and groupid.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public abstract void updateCache(MultiDimensionalObject dimension,
			String groupId);

	/**
	 * Adds the new group taking the groupId.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public abstract void addNewGroup(MultiDimensionalObject dimension,
			String groupId);

	/**
	 * Checks if the Given Dimension Exists
	 *
	 * @param path the path
	 * @return true, if successful
	 */
	public abstract boolean ifGroupIdExistsFor(String path);

	/**
	 * Gets the all groups.
	 *
	 * @return the all groups
	 */
	public abstract List<String> getAllGroups();

}