package com.shop.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片压缩处理工具类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-04 23:01:11
 */
public class ThumbnailatorUtils {

	// 商品图
	public final static int PRODUCT_BIGIMAGE_TYPE = 1;

	// 商品图 大图
	public final static int PRODUCT_IMAGE_BIGTYPE = 11;

	// 商品图 中图
	public final static int PRODUCT_IMAGE_MEDIUMTYPE = 12;

	// 商品图 小图
	public final static int PRODUCT_IMAGE_SMALLTYPE = 13;

	// 商品缩略图
	public final static int PRODUCT_SMALLIMAGE_TYPE = 2;

	// 商品分类缩略图
	public final static int PRODUCT_CATEGORY_TYPE = 3;

	// 资讯分类缩略图
	public final static int INFO_CATEGORY_TYPE = 4;

	// 网站logo
	public final static int SITE_LOGIN_TYPE = 5;

	// 会员中心图片上传
	public final static int CUSTOMER_IMAGE_TYPE = 6;

	// 商品品牌图片
	public final static int PRODUCT_BRAND_TYPE = 7;

	// 资讯内容缩略图
	public final static int INFO_CONTENT_SMALL_TYPE = 8;

	/**
	 * 指定大小并且按照比例进行缩放 size(width,height) 若图片横比200小，高比300小，不变
	 * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * 
	 * @throws IOException
	 */
	public static void compressBySizeAndScale(String source, Integer type, String target) throws IOException {
		int width = 0, height = 0;
		switch (type) {
		case ThumbnailatorUtils.PRODUCT_BIGIMAGE_TYPE: {
			// 商品图片宽度 640px,高度 370px
			width = 640;
			height = 370;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_SMALLIMAGE_TYPE: {
			// 商品缩略图 大小 150px*150px
			width = 150;
			height = 150;
			break;
		}
		case ThumbnailatorUtils.INFO_CATEGORY_TYPE: {
			// 咨询分类图 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_CATEGORY_TYPE: {
			// 商品分类图 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_BRAND_TYPE: {
			// 商品品牌 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.SITE_LOGIN_TYPE: {
			// 网站logo 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.CUSTOMER_IMAGE_TYPE: {
			// 会员中心 160px *160px
			width = 160;
			height = 160;
			break;
		}
		case ThumbnailatorUtils.INFO_CONTENT_SMALL_TYPE: {
			// 资讯 内容缩略图
			width = 180;
			height = 180;
			break;
		}
		}
		Thumbnails.of(source).size(width, height).toFile(target);
	}

	/**
	 * 按照比例进行缩放
	 * 
	 * @throws IOException
	 */
	public static void compressByScale(String source, Integer type, String target) throws IOException {
		float scale = 1.00f;
		BufferedImage sourceImage = ImageIO.read(new File(source));
		int sourceImageWidth = sourceImage.getWidth();
		switch (type) {
		case ThumbnailatorUtils.PRODUCT_BIGIMAGE_TYPE: {
			// 商品图片宽度 640px
			scale = (float) (640 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.PRODUCT_IMAGE_BIGTYPE: {
			// 商品图片宽度 640px
			scale = (float) (600 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.PRODUCT_IMAGE_MEDIUMTYPE: {
			// 商品图片宽度 640px
			scale = (float) (300 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.PRODUCT_IMAGE_SMALLTYPE: {
			// 商品图片宽度 640px
			scale = (float) (170 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.PRODUCT_SMALLIMAGE_TYPE: {
			// 商品缩略图 宽度 150px
			scale = (float) (150 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.INFO_CATEGORY_TYPE: {
			// 咨询分类图宽度 120px
			scale = (float) (120 * 1.0 / sourceImageWidth);
			break;
		}

		case ThumbnailatorUtils.PRODUCT_CATEGORY_TYPE: {
			// 商品分类图 宽度120px
			scale = (float) (120 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.PRODUCT_BRAND_TYPE: {
			// 商品品牌 120px * 120px
			scale = (float) (120 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.SITE_LOGIN_TYPE: {
			// 网站logo 宽度 120px
			scale = (float) (120 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.CUSTOMER_IMAGE_TYPE: {
			// 会员中心 160px *160px
			scale = (float) (160 * 1.0 / sourceImageWidth);
			break;
		}
		case ThumbnailatorUtils.INFO_CONTENT_SMALL_TYPE: {
			// 资讯 内容缩略图
			scale = (float) (180 * 1.0 / sourceImageWidth);
			break;
		}
		}
		Thumbnails.of(source).scale(scale).toFile(target);
	}

	/**
	 * 不按照比例，指定大小进行缩放
	 * 
	 * @throws IOException
	 */
	public static void compressBySize(String source, Integer type, String target) throws IOException {
		int width = 0, height = 0;
		switch (type) {
		case ThumbnailatorUtils.PRODUCT_BIGIMAGE_TYPE: {
			// 商品图片宽度 640px,高度 370px
			width = 640;
			height = 370;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_SMALLIMAGE_TYPE: {
			// 商品缩略图 大小 150px*150px
			width = 150;
			height = 150;
			break;
		}
		case ThumbnailatorUtils.INFO_CATEGORY_TYPE: {
			// 咨询分类图 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_BRAND_TYPE: {
			// 商品品牌 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.PRODUCT_CATEGORY_TYPE: {
			// 商品分类图 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.SITE_LOGIN_TYPE: {
			// 网站logo 120px * 120px
			width = 120;
			height = 120;
			break;
		}
		case ThumbnailatorUtils.CUSTOMER_IMAGE_TYPE: {
			// 会员中心 160px *160px
			width = 160;
			height = 160;
			break;
		}
		case ThumbnailatorUtils.INFO_CONTENT_SMALL_TYPE: {
			// 资讯 内容缩略图
			width = 180;
			height = 180;
			break;
		}
		}
		/**
		 * keepAspectRatio(false) 默认是按照比例缩放的
		 */
		Thumbnails.of(source).size(width, height).keepAspectRatio(false).toFile(target);
	}
}
