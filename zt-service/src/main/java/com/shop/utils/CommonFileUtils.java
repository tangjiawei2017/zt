package com.shop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

//文件操作工具类
public class CommonFileUtils {
	private static Logger logger = Logger.getLogger(CommonFileUtils.class);

	public static boolean copyFile(File oldFile, File newFile) {
		try {
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			InputStream in = new FileInputStream(oldFile);
			OutputStream out = new FileOutputStream(newFile);
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = in.read(bytes)) != -1) {
				out.write(bytes, 0, len);
			}
			in.close();
			out.close();
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param source
	 *            临时文件路径(以 /temp/abc.png 格式)
	 * @param module
	 *            模块
	 * 
	 * @return 新的文件路径(相对路径)
	 */
	public static String copyFile(String source, String module) {
		try {
			// 对source 校验 只有 temp目录文件才 copy
			if (source.startsWith(File.separator + IConstant.TEMP)) {
				File dir = new File(WebSiteConstant.REPOSITORY + File.separator + module);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				InputStream in = new FileInputStream(new File(WebSiteConstant.CLUSTER + source));
				OutputStream out = new FileOutputStream(
						new File(dir.getAbsolutePath(), source.substring(source.lastIndexOf('/'), source.length())));
				byte[] bytes = new byte[1024];
				int len = -1;
				while ((len = in.read(bytes)) != -1) {
					out.write(bytes, 0, len);
				}
				in.close();
				out.close();
				return File.separator + IConstant.REPOSITORY + File.separator + module
						+ source.substring(source.lastIndexOf('/'), source.length());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return source;
	}

	/**
	 * @param source
	 *            临时文件路径(以 /temp/abc.png 格式)
	 * @param module
	 *            模块
	 * 
	 * @return 新的文件路径(相对路径) 并压缩处理
	 */
	public static String copyFileAndCompress(String source, String module, int compressType, int moduleType) {
		try {
			// 对source 校验 只有 temp目录文件才 copy
			if (source.startsWith(File.separator + IConstant.TEMP)) {
				File dir = new File(WebSiteConstant.REPOSITORY + File.separator + module);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String sourcepath = WebSiteConstant.CLUSTER + source;
				String targetpath = dir.getAbsolutePath() + source.substring(source.lastIndexOf('/'), source.length());
				logger.info("sourcepath=========>" + sourcepath);
				logger.info("targetpath=========>" + targetpath);
				// 图片压缩处理
				switch (compressType) {
				case 1: {
					ThumbnailatorUtils.compressBySizeAndScale(sourcepath, moduleType, targetpath);
					break;
				}
				case 2: {
					ThumbnailatorUtils.compressByScale(sourcepath, moduleType, targetpath);
					break;
				}
				case 3: {
					ThumbnailatorUtils.compressBySize(sourcepath, moduleType, targetpath);
				}
				}
				return File.separator + IConstant.REPOSITORY + File.separator + module
						+ source.substring(source.lastIndexOf('/'), source.length());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return source;
	}
}
