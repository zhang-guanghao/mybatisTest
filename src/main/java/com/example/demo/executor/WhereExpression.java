/**
 * 
 */
package com.example.demo.executor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 *
 */
public class WhereExpression {
	private DBColumn column;
	private String op;
	private List<Object> values = new ArrayList<Object>();
	

	public DBColumn getColumn() {
		return column;
	}
	public void setColumn(DBColumn column) {
		this.column = column;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
}
