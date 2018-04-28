package cn.dewro.remex.example.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import cn.dewro.remex.quartz.job.BaseQuartzJob;

/**
 * 消息发送job2
 * 
 * @author Qiaoxin.Hong
 *
 */
@Component
public class SendTwoJob extends BaseQuartzJob {

	/**
	 * job业务实际处理
	 * @param context
	 */
	@Override
	public void handle(JobExecutionContext context) {
		String curTime = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
		System.out.printf("========== %s send two job  ===========\n", curTime);
	}
}
