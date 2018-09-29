package com.example.demo.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ExecutorDAO;

@Service
public class Executor {
	@Autowired
	private ExecutorDAO executorDAO;
	
	@Transactional
	public void insert(ExecuteParam param) {
		this.executorDAO.insert(param);
	}
	
	@Transactional
	public void update(ExecuteParam param) {
		this.executorDAO.update(param);
	}
}
