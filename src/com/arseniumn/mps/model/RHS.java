package com.arseniumn.mps.model;

public class RHS {

	private String name, TypeConstraint, TypeConstraint2;
	private double value, Value2;

	public RHS(String aVariable, String aTypeConstraint, String aValue) {
		this.name = aVariable;
		this.TypeConstraint = aTypeConstraint;
		this.value = Double.parseDouble(aValue);	
	}

	public RHS(String aVariable, String aTypeConstraint, String aValue, String aTypeConstraint2, String aValue2) {
		this.name = aVariable;
		this.TypeConstraint = aTypeConstraint;
		this.value = Double.parseDouble(aValue);	
		this.TypeConstraint2 = aTypeConstraint2;
		this.Value2 = Double.parseDouble(aValue2);	
	}

	public String getName() {
		return name;
	}

	public String getTypeConstraint() {
		return TypeConstraint;
	}

	public double getValue() {
		return value;
	}

	public String getTypeConstraint2() {
		return TypeConstraint2;
	}

	public void setTypeConstraint2(String typeConstraint2) {
		TypeConstraint2 = typeConstraint2;
	}

	public double getValue2() {
		return Value2;
	}

	public void setValue2(double value2) {
		Value2 = value2;
	}	
	
	
}
