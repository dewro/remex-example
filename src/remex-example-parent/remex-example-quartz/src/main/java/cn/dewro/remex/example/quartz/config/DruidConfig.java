package cn.dewro.remex.example.quartz.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * druid数据库连接池
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class DruidConfig {

	/**
	 * 数据源
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
	}
}
