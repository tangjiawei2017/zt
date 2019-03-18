package com.shop.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目启动时,开启清除临时目录定时任务 清除时间: 每周一早上 2:00
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-04 19:05:21
 */
public class CleanTempScheduleListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	@SuppressWarnings("resource")
	public void contextInitialized(ServletContextEvent event) {

	}
}
