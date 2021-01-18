package com.anadoluefes.hr.expenses.model;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class ExpenseItemModel implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label("Expense Type")
	private java.lang.String expenseType;

	@org.kie.api.definition.type.Label(value = "Expense Value")
	private java.math.BigDecimal expenseValue;

	public ExpenseItemModel() {
	}

	public java.lang.String getExpenseType() {
		return this.expenseType;
	}

	public void setExpenseType(java.lang.String expenseType) {
		this.expenseType = expenseType;
	}

	public java.math.BigDecimal getExpenseValue() {
		return this.expenseValue;
	}

	public void setExpenseValue(java.math.BigDecimal expenseValue) {
		this.expenseValue = expenseValue;
	}

	public ExpenseItemModel(java.lang.String expenseType,
			java.math.BigDecimal expenseValue) {
		this.expenseType = expenseType;
		this.expenseValue = expenseValue;
	}
	
	public String toString() {
    return "ExpenseItemModel: ' expenseType: " + this.expenseType + "', expenseValue: '" + this.expenseValue + "'";
}


}