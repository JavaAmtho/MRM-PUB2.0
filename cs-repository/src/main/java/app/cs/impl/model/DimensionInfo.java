package app.cs.impl.model;

public class DimensionInfo {

	private String name;
	private String managerName;
	private String startDate;
	private String endDate;
	private String budgetOwner;
	private String currency;
	private String budget;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBudgetOwner() {
		return budgetOwner;
	}

	public void setBudgetOwner(String budgetOwner) {
		this.budgetOwner = budgetOwner;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String amount) {
		this.budget = amount;
	}
}
