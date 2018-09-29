/**
 * 
 */
package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.executor.DBColumn;
import com.example.demo.executor.ExecuteParam;
import com.example.demo.executor.Executor;
import com.example.demo.executor.WhereExpression;

/**
 * @author zhang
 *
 */
@Component
public class Test implements ApplicationListener<ContextRefreshedEvent> {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	private Executor executor;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		testUpdate2();
	}
	
	private void testInsert1() {
		long now = System.currentTimeMillis();
		Map<DBColumn, Object> row = new LinkedHashMap<DBColumn, Object>();
		row.put(new DBColumn("ID"), String.format("%d", now));
		row.put(new DBColumn("NAME"), String.format("NAME_%d", now));
		row.put(new DBColumn("CALLTIME", true), "2018-09-29 12:00:00");
		
		ExecuteParam param = new ExecuteParam();
		param.setTableName("ZGHWSTEST");
		param.setRow(row);
		
		executor.insert(param);
	}
	
	private void testUpdate1() {
		long now = System.currentTimeMillis();
		Map<DBColumn, Object> row = new LinkedHashMap<DBColumn, Object>();
		row.put(new DBColumn("ID"), String.format("%d", now));
		row.put(new DBColumn("NAME"), String.format("NAME_%d", now));
		row.put(new DBColumn("CALLTIME", true), "2018-09-29 12:00:00");
		
		ExecuteParam param = new ExecuteParam();
		param.setTableName("ZGHWSTEST");
		param.setRow(row);
		
		executor.insert(param);
		
		row.clear();
		row.put(new DBColumn("AGE"), 20);
		
		List<WhereExpression> where = new ArrayList<WhereExpression>();
		WhereExpression whereItem = new WhereExpression();
		whereItem.setColumn(new DBColumn("ID"));
		whereItem.setOp("=");
		whereItem.getValues().add(String.format("%d", now));
		where.add(whereItem);
		
		param.setTableName("ZGHWSTEST");
		param.setRow(row);
		param.setWhere(where);
		
		executor.update(param);
	}
	
	private void testUpdate2() {
		long now = System.currentTimeMillis();
		Date nowDate = new Date();
		Map<DBColumn, Object> row = new LinkedHashMap<DBColumn, Object>();
		row.put(new DBColumn("ID"), String.format("%d", now));
		row.put(new DBColumn("NAME"), String.format("NAME_%d", now));
		row.put(new DBColumn("CALLTIME", true), dateFormat.format(nowDate));
		
		ExecuteParam param = new ExecuteParam();
		param.setTableName("ZGHWSTEST");
		param.setRow(row);
		
		executor.insert(param);
		
		row.clear();
		row.put(new DBColumn("AGE"), 30);
		
		List<WhereExpression> where = new ArrayList<WhereExpression>();
		WhereExpression whereItem = new WhereExpression();
		whereItem.setColumn(new DBColumn("CALLTIME", true));
		whereItem.setOp("=");
		whereItem.getValues().add(dateFormat.format(nowDate));
		where.add(whereItem);
		
		param.setTableName("ZGHWSTEST");
		param.setRow(row);
		param.setWhere(where);
		
		executor.update(param);
	}
}
