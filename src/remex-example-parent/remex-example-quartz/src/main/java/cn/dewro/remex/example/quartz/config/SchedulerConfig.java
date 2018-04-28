package cn.dewro.remex.example.quartz.config;

import javax.sql.DataSource;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import cn.dewro.remex.example.quartz.job.SendOneJob;
import cn.dewro.remex.example.quartz.job.SendTwoJob;
import cn.dewro.remex.quartz.config.BaseQuartzSchedulerConfig;

/**
 * quartz任务调度配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class SchedulerConfig extends BaseQuartzSchedulerConfig {
	
	/**
	 * 任务调度类
	 * @return
	 */
	@ConfigurationProperties(prefix = "remex.exmaple.quartz.scheduler")
	@Bean
	public SchedulerFactoryBean getSchedulerFactory(DataSource dataSource
			, @Qualifier("sendOneTrigger") CronTrigger sendOneTrigger
			, @Qualifier("sendTwoTrigger") CronTrigger sendTwoTrigger) {
		try {
			SchedulerFactoryBean factoryBean = buildScheduler(dataSource);
			
			//注册触发器
			factoryBean.setTriggers(sendOneTrigger, sendTwoTrigger);
			
			return factoryBean;
		} catch (Exception e) {
			logger.error("Scheduler config error!", e);
			return null;
		}
	}
	
	

	/**
	 * job1任务调度明细
	 * @return
	 */
	@Bean("sendOneJobDetail")
	@ConfigurationProperties(prefix = "send.one.scheduler.job")
	public JobDetailFactoryBean getSendOneJobDetail() {
		JobDetailFactoryBean factoryBean = buildJobDetail(SendOneJob.class);
		return factoryBean;
	}
	
	/**
	 * job1任务调度触发器
	 * @param jobDetail
	 * @return
	 */
	@Bean("sendOneTrigger")
	@ConfigurationProperties(prefix = "send.one.scheduler.trigger")
	public CronTriggerFactoryBean getSendOneTrigger(@Qualifier("sendOneJobDetail") JobDetail jobDetail) {
		CronTriggerFactoryBean factoryBean = buildTrigger(jobDetail);
		return factoryBean;
	}
	
	/**
	 * job2任务调度明细
	 * @return
	 */
	@Bean("sendTwoJobDetail")
	@ConfigurationProperties(prefix = "send.two.scheduler.job")
	public JobDetailFactoryBean getSendTwoJobDetail() {
		JobDetailFactoryBean factoryBean = buildJobDetail(SendTwoJob.class);
		return factoryBean;
	}
	
	/**
	 * job2任务调度触发器
	 * @param jobDetail
	 * @return
	 */
	@Bean("sendTwoTrigger")
	@ConfigurationProperties(prefix = "send.two.scheduler.trigger")
	public CronTriggerFactoryBean getSendTwoTrigger(@Qualifier("sendTwoJobDetail") JobDetail jobDetail) {
		CronTriggerFactoryBean factoryBean = buildTrigger(jobDetail);
		return factoryBean;
	}
	
	
	
	
	
	
	
	
}
