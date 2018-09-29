/**
 * 
 */
package com.example.demo.executor;

/**
 * @author zhang
 *
 */
public class DBColumn {
	private String name;
	private boolean isDate;
	
	public DBColumn() {
		
	}
	
	public DBColumn(String name) {
		this.name = name;
		this.isDate = false;
	}
	
	public DBColumn(String name, boolean isDate) {
		this.name = name;
		this.isDate = isDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDate() {
		return isDate;
	}
	public void setDate(boolean isDate) {
		this.isDate = isDate;
	}
}