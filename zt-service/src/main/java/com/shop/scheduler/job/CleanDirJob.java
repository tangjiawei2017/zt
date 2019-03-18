package com.shop.scheduler.job;

import java.io.File;

import com.shop.utils.WebSiteConstant;

/**
 * 清理临时目录任务
 * 
 */
public class CleanDirJob {

	public void execute() {
		File dir = new File(WebSiteConstant.TEMP);
		if (dir.exists() && dir.isDirectory() && dir.listFiles().length > 0) {
			deleteDir(dir);
		}
	}

	/**
	 * 清空目录里面的文件
	 * 
	 * @param dir
	 * @return
	 */
	private boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
}
