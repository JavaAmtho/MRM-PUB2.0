package app.cs.impl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Component;

import com.cs.data.api.core.GenericDomain;

@Component
public class MultiDimensionalObject implements Serializable, GenericDomain {

	public String getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}

	public String getActualImage() {
		return actualImage;
	}

	public void setActualImage(String actualImage) {
		this.actualImage = actualImage;
	}

	public String getPreviewType() {
		return previewType;
	}

	public void setPreviewType(String previewType) {
		this.previewType = previewType;
	}

	private static final long serialVersionUID = 1L;
	private final String PAGE = "page";
	private String id;
	private String type;
	private String path;
	private String name;
	private String title;
	private String previewImage;
	private String actualImage;
	private String previewType;
	private boolean isFolder;
	private String manager;
	private String startDate;
	private String endDate;
	private String budgetOwner;
	private String currency;
	private String budget;

	public String getManager() {
		return dimensionInfo.getManagerName();
	}

	public String getStartDate() {
		return dimensionInfo.getStartDate();
	}

	public String getEndDate() {
		return dimensionInfo.getEndDate();
	}

	public String getBudgetOwner() {
		return dimensionInfo.getBudgetOwner();
	}

	public String getCurrency() {
		return dimensionInfo.getCurrency();
	}

	public String getBudget() {
		return dimensionInfo.getBudget();
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	private List<Assortment> assortments;
	private List<MultiDimensionalObject> items;

	@JsonProperty("Items")
	public List<MultiDimensionalObject> getItems() {
		return children;
	}

	private List<MultiDimensionalObject> children;

	private List<Product> products;

	private DimensionInfo dimensionInfo;

	public DimensionInfo getDimensionInfo() {
		return dimensionInfo;
	}

	public void setDimensionInfo(DimensionInfo dimensionInfo) {
		this.dimensionInfo = dimensionInfo;
	}

	public MultiDimensionalObject() {
	}

	public List<Assortment> getAssortments() {
		return assortments;
	}

	public void setAssortments(List<Assortment> assortments) {
		this.assortments = assortments;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/** The group ids. */
	private List<String> groupIds;
	private String image;

	public List<Assortment> getAssortment() {
		return assortments;
	}

	public void setAssortment(List<Assortment> assortment) {
		this.assortments = assortment;
	}

	public List<String> getGroupId() {
		return groupIds;
	}

	public MultiDimensionalObject(String id, String type, String title,
			String name, String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.path = path;
	}

	public MultiDimensionalObject(String id, String type, String path,
			String name, List<String> groupId,
			List<MultiDimensionalObject> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupIds = groupId;
		this.children = children;
	}

	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	public List<MultiDimensionalObject> getChildren() {
		return children;
	}

	public void setChildren(List<MultiDimensionalObject> children) {
		this.children = children;
	}

	public MultiDimensionalObject(String name, String type, String path,
			boolean isFolder) {
		this.id = name;
		this.name = name;
		this.title = name;
		this.type = type;
		this.path = path;
		this.isFolder = isFolder;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getObjectKey() {
		return "DIMENSION";
	}

	@Override
	public String getKey() {
		return getId();
	}

	@Override
	public String toString() {
		return "DimensionModel [id=" + id + ", type=" + type + ", path=" + path
				+ ", title=" + title + ", name=" + name + ", groupIds="
				+ groupIds + ", children=" + children + "]";
	}

	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

	public void addToGroupId(String groupId) {
		if (groupIds == null) {
			groupIds = new ArrayList<String>();
			groupIds.add(groupId);
		} else {

			groupIds.add(groupId);
		}

	}

	public boolean hasChildren() {
		return this.children == null ? false : true;
	}

	public boolean isPage() {
		return type == PAGE ? true : false;
	}

	public void addchild(MultiDimensionalObject contentObject) {
		List<MultiDimensionalObject> newChildren;
		if (this.children == null) {
			newChildren = new ArrayList<MultiDimensionalObject>();
			newChildren.add(contentObject);
			this.children = newChildren;

		} else {
			this.children.add(contentObject);
		}
	}

	public void addAssortment(Assortment assortment) {
		List<Assortment> newChildren;
		if (this.assortments == null) {
			newChildren = new ArrayList<Assortment>();
			newChildren.add(assortment);
			this.assortments = newChildren;

		} else {
			this.assortments.add(assortment);
		}
	}

	public void removeChild(MultiDimensionalObject contentObject) {
		if (children != null) {
			this.children.remove(contentObject);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PAGE == null) ? 0 : PAGE.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result
				+ ((groupIds == null) ? 0 : groupIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		MultiDimensionalObject other = (MultiDimensionalObject) obj;
		if (id.equals(other.id))
			return true;
		else
			return false;

	}

	public String getImageUrl() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;

	}
}
