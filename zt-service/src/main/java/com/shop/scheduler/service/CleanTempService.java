package com.shop.scheduler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shop.scheduler.job.CleanDirJob;

/**
 * 清除 临时文件 service
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-04 19:07:43
 * 
 *       每周一凌晨2点 执行删除临时目录
 */
@Component
public class CleanTempService {

	@Scheduled(cron = "0 0 2 ? * MON")
	// 每隔1分钟执行一次
	public void cleanTempDir() {
		new CleanDirJob().execute();
	}
}
