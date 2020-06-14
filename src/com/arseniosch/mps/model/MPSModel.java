package com.arseniosch.mps.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class MPSModel {
	
	private String name, type;
	private List<Row> rows = new ArrayList<Row>();
	private List<Column> columns = new ArrayList<Column>();
	private List<RHS> rhs = new ArrayList<RHS>();
	private LinkedHashSet<String> set = new LinkedHashSet<String>(); //Usage of a LinkedHashSet (and not HashSet) is to have all elements in order that we added them,e.g. ascending
	private LinkedHashMap<String, IndexTypeModel> map = new LinkedHashMap<String, IndexTypeModel>(); //Usage of a LinkedHashMap (and not HashMap) is to have all elements in order that we added them,e.g. ascending
	private LinkedHashMap<String, Integer> map_var = new LinkedHashMap<String, Integer>(); //Usage of a LinkedHashMap (and not HashMap) is to have all elements in order that we added them,e.g. ascending
	private double[][] A;
	private double[] b, c;
	private int[] MinMax = new int[1];
	private int[] Eqin;
	private int m, n;
	private int ind_zero;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;	
	}

	public List<Row> getRows() {
		return rows;
	}

	public List<Column> getColumns() {
		return columns;
	}
	
	public List<RHS> getRHS() {
		return rhs;
	}
	
	public void constructArrays() {		
		/* Construction of A[][], c[], b[] arrays
		 * Get the number of rows of the array A[rows][columns] 
		 */
		int index=0;
		for(Row row : this.rows) {
			map.put(row.getTypeConstraint(), new IndexTypeModel(index,  row.getMPSValue()));
			set.add(row.getTypeConstraint());
			index++;
		}
		m = set.size() - 1; // -1 because the set contains and the objective value
		set.clear();
		//map.forEach((key, value) -> System.out.println(key + ":" + value.getType()));

		/* 
		 * Get the number of columns of the array A[rows][columns] by adding the variables in a set
		 * A set holds no-duplicates, so we get this -> set = [X1 X2 X3 X4], if the variables are only 4
		 */
		index = 0;
		for(Column column : this.columns) {
			set.add(column.getVariable());
			if(!map_var.containsKey(column.getVariable())) {
				map_var.put(column.getVariable(), index);
				index++;
			}
		}
		n = set.size();	
		A = new double[m+1][n];
		b = new double[m];
		c = new double[n];
		Eqin = new int[m];
		
		//Method to insert the values in arrays
		insertInfo();
	}

	private void insertInfo() {
		//Insert value in MinMax[] array
		if(this.type.equalsIgnoreCase("MIN")) MinMax[0] = -1;
		else if(this.type.equalsIgnoreCase("MAX")) MinMax[0] = 1;
	
		//Insert values in Eqin[] array
		for(int i=0; i<this.rows.size()-1; i++) {
			if(this.rows.get(i).getMPSValue().equalsIgnoreCase("E")) Eqin[i] = 0;
			else if(this.rows.get(i).getMPSValue().equalsIgnoreCase("G")) Eqin[i] = 1;
			else if(this.rows.get(i).getMPSValue().equalsIgnoreCase("L")) Eqin[i] = -1;
		}
		
		//Insert values in b[] array
		for(int i=0; i<this.rhs.size()-1; i++) {
			int index = map.get(this.rhs.get(i).getTypeConstraint()).getIndex();
			b[index] = this.rhs.get(i).getValue();
			if(this.rhs.get(i).getValue2() != 0) {
				index = map.get(this.rhs.get(i).getTypeConstraint2()).getIndex();
				b[index] = this.rhs.get(i).getValue2();
			}
		}
		
		// Insert values in A[][], c[] arrayz
		int i=0, j=0;
		for(int k=0; k<this.columns.size(); k++) {
			if(this.columns.get(k).getTypeObjConstraint()!=null) {
				if(map.get(this.columns.get(k).getTypeObjConstraint()).getType().equalsIgnoreCase("N")) {
					i = map_var.get(this.columns.get(k).getVariable());
					c[i] = this.columns.get(k).getObjCoefficient(); 
				}
				else {
					i = map.get(this.columns.get(k).getTypeObjConstraint()).getIndex();
					j = map_var.get(this.columns.get(k).getVariable());
					A[i][j] = this.columns.get(k).getObjCoefficient();	
				}
			}			
			if(map.get(this.columns.get(k).getTypeConstraint()).getType().equalsIgnoreCase("N")) {
				i = map_var.get(this.columns.get(k).getVariable());
				c[i] = this.columns.get(k).getValue();
			}	
			else {
				i = map.get(this.columns.get(k).getTypeConstraint()).getIndex();
				j = map_var.get(this.columns.get(k).getVariable());
				A[i][j] = this.columns.get(k).getValue();
			}
		}
		//Find one row that has all of its elements as zeros
		outerloop:
		for(i=0; i<A.length; i++) {
			int counter=0;
			for(j=0; j<A[i].length; j++) {
				if(A[i][j] == 0) counter++;
				if(counter == A[i].length) {
					ind_zero = i;
					break outerloop;
				}
			}
		}
	}

	public double[][] getA() {
		return A;
	}

	public double[] getB() {
		return b;
	}

	public double[] getC() {
		return c;
	}

	public int[] getMinMax() {
		return MinMax;
	}

	public int[] getEqin() {
		return Eqin;
	}

	public int getInd_zero() {
		return ind_zero;
	}	
	
	
	
}
