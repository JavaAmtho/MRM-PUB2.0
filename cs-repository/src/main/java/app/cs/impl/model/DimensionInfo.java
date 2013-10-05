package app.cs.impl.model;

public class DimensionInfo {

	private String actualImage;
	private String budget;
	private String budgetOwner;
	private String currency;
	private String endDate;
	private String managerName;
	private String name;
	private String previewImage;
	private String previewType;
	private String startDate;

	public String getActualImage() {
		return actualImage;
	}

	public String getBudget() {
		return budget;
	}

	public String getBudgetOwner() {
		return budgetOwner;
	}

	public String getCurrency() {
		return currency;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getName() {
		return name;
	}

	public String getPreviewImage() {
		return previewImage;
	}

	public String getPreviewType() {
		return previewType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setActualImage(String actualImage) {
		this.actualImage = actualImage;
	}

	public void setBudget(String amount) {
		this.budget = amount;
	}

	public void setBudgetOwner(String budgetOwner) {
		this.budgetOwner = budgetOwner;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}

	public void setPreviewType(String previewType) {
		this.previewType = previewType;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
