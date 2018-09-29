/**
 * 
 */
package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.executor.ExecuteParam;

/**
 * @author zhang
 *
 */
@Repository
public interface ExecutorDAO {
	void insert(@Param("param") ExecuteParam param);
	void update(@Param("param") ExecuteParam param);
}
