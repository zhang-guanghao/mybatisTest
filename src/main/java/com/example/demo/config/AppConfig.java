package com.example.demo.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.HiappBaseDAO;
import com.github.pagehelper.PageInterceptor;

@Configuration
@EnableTransactionManagement
public class AppConfig {
	@Bean
	@Autowired
	public DataSourceTransactionManager txManager(DriverManagerDataSource dataSource) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource);
		return txManager;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.example.demo");
		mapperScannerConfigurer.setAnnotationClass(Repository.class);
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		mapperScannerConfigurer.setMarkerInterface(HiappBaseDAO.class);
		return mapperScannerConfigurer;
	}
	
	@Bean
	@Qualifier("sqlSessionTemplate")
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = null;
		try {
			sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSessionTemplate;
	}
	
	@Bean
	@Autowired
	public SqlSessionFactoryBean sqlSessionFactory(DriverManagerDataSource dataSource,
			org.apache.ibatis.session.Configuration configuration) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();			//只加载一个绝对匹配Resource,且通过ResourceLoader.getResource进行加载			Resource resources=resolver.getResource("classpath:META-INF/INDEX.LIST");
		Resource[] mapperLocations = null;
		try {
			String locationPattern = "classpath*:com/example/**/*Mapper.xml";
			
			mapperLocations = resolver.getResources(locationPattern);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Interceptor> pluginList = new ArrayList<Interceptor>();
		pluginList.add(new PageInterceptor());
		Interceptor[] plugins = new Interceptor[pluginList.size()];
		pluginList.toArray(plugins);
		
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		sqlSessionFactoryBean.setPlugins(plugins);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.184:1521:orcl");
		dataSource.setUsername("HI_APP_TENANT1222");
		dataSource.setPassword("pass");
	
		return dataSource;
	}
	
	@Bean
	public org.apache.ibatis.session.Configuration configuration() {
		org.apache.ibatis.session.Configuration configuration = null;
		Resource configLocation = new ClassPathResource("mybatis-config.xml");
		Properties configurationProperties = new Properties();
		
		try {
			XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configLocation.getInputStream(), null, configurationProperties);
			configuration = xmlConfigBuilder.getConfiguration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return configuration;
	}
}
