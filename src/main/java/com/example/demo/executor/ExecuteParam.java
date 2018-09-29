/**
 * 
 */
package com.example.demo.executor;

import java.util.List;
import java.util.Map;

/**
 * @author zhang
 *
 */
public class ExecuteParam {
	private String tableName;
	private Map<DBColumn, Object> row;
	private List<WhereExpression> where;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<DBColumn, Object> getRow() {
		return row;
	}
	public void setRow(Map<DBColumn, Object> row) {
		this.row = row;
	}
	public List<WhereExpression> getWhere() {
		return where;
	}
	public void setWhere(List<WhereExpression> where) {
		this.where = where;
	}
}
