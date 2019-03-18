package com.shop.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductMapper;
import com.shop.model.ProductDO;
import com.shop.service.IProductService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.CommonUtils;
import com.shop.utils.IConstant;
import com.shop.utils.ProductConvert;
import com.shop.utils.WebSiteConstant;
import com.shop.vo.Condition;
import com.shop.vo.ProductVO;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	private Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Override
	public void addProduct(ProductDO productDO) throws RuntimeException {
		Date date = new Date();
		productDO.setCreated(date);
		productDO.setModified(date);
		// 商品缩略图
		if (StringUtils.isNotEmpty(productDO.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			productDO.setImage(CommonFileUtils.copyFile(productDO.getImage(), IConstant.PRODUCT));
		}
		saveProductImages(productDO);
		saveProductDesc(productDO);
		this.productMapper.addProduct(productDO);
	}

	@Override
	public ProductDO findProductDoById(Integer id) {
		return productMapper.findProductDoById(id);
	}

	@Override
	public void delProduct(Integer id) {
		this.productMapper.delProduct(id);
	}

	/**
	 * 保存图片，并修改其路径
	 * 
	 * 从临时目录保存到 对应路径
	 * 
	 * 图片3个大小 : big 600*600 medium 300*300 small 170*170 按宽度处理
	 * 
	 * @param request
	 * @param product
	 */
	private void saveProductImages(ProductDO product) throws RuntimeException {
		File dirs = new File(WebSiteConstant.PRODUCT);
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		if (StringUtils.isNotEmpty(product.getImages())) {
			String[] images = product.getImages().split(",");
			StringBuffer sb = new StringBuffer();
			for (String img : images) {
				String suffix = img.substring(img.lastIndexOf('.'), img.length());
				String imgName = CommonUtils.getRandomString(10);
				File oldFile = new File(WebSiteConstant.TEMP, img);
				if (!oldFile.exists()) {
					// 如果临时文件不存在,表明商品编辑时,没有改变该图片,所以不需要操作
					sb.append(img).append(",");
				} else {
					File newFile = new File(dirs, imgName + suffix);
					logger.info("newFile========>" + dirs.getAbsolutePath() + File.separator + imgName + suffix);
					if (CommonFileUtils.copyFile(oldFile, newFile)) {
						sb.append(File.separator).append(IConstant.REPOSITORY).append(File.separator)
								.append(IConstant.PRODUCT).append(File.separator).append(imgName).append(suffix)
								.append(",");
					}
				}
			}
			product.setImages(sb.substring(0, sb.length() - 1));
		}
	}

	// 处理商品的描述出的图片
	private void saveProductDesc(ProductDO product) throws RuntimeException {
		File dirs = new File(WebSiteConstant.PRODUCT);
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		String desc = product.getDescription();
		if (desc != null) {
			Document doc = Jsoup.parse(desc);
			Elements elements = doc.getElementsByTag("img");
			for (Element element : elements) {
				String filePath = element.attr("src");
				String suffix = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
				String imgName = CommonUtils.getRandomString(10);
				String path = WebSiteConstant.CLUSTER + filePath;
				File oldFile = new File(path);
				if (filePath.startsWith(File.separator + IConstant.TEMP)) {
					// 图片路径以 /temp 开头,则需要从临时目录拷贝到 图片目录
					File newFile = new File(dirs, imgName + suffix);
					if (CommonFileUtils.copyFile(oldFile, newFile)) {
						StringBuffer sb = new StringBuffer();
						sb.append(File.separator).append(IConstant.REPOSITORY).append(File.separator)
								.append(IConstant.PRODUCT).append(File.separator).append(imgName).append(suffix);
						logger.info("productDesc ========>" + sb.toString());
						element.attr("src", sb.toString());
					}
				}
			}
			product.setDescription(doc.html());
		}
	}

	@Override
	public List<ProductDO> findProductsByCondition(Integer currpage, Integer limit, Condition condition) {
		int start = (currpage - 1) * limit;
		List<ProductDO> productList = this.productMapper.findProductsByCondition(start, limit, condition);
		return productList;
	}

	@Override
	public List<ProductVO> findProductsVOByCondition(Integer currpage, Integer limit, Condition condition) {
		List<ProductDO> plist = this.findProductsByCondition(currpage, limit, condition);
		return ProductConvert.convertProductDOToVO(plist);
	}

	@Override
	public Integer countProduct(Condition condition) {
		return productMapper.countProduct(condition);
	}

	@Override
	public void updateSaleStatus(Integer id, Integer sale) {
		productMapper.updateProductStatus(id, sale);
	}

	@Override
	public void batchUpdateProductsSaleOn(List<Integer> ids) {
		if (ids != null && ids.size() > 0) {
			productMapper.updateProductsSaleOn(ids);
		}
	}

	@Override
	public void batchUpdateProductPutOff(Integer[] ids) {
		productMapper.updateProductsPutOff(ids);
	}

	@Override
	public ProductDO findProductByCode(String code) {
		return productMapper.findProductByCode(code);
	}

	@Override
	public List<ProductDO> findSimpleProducts() {
		return productMapper.findSimpleProducts();
	}

	@Override
	public Integer countProductByCategoryId(Integer id) {
		return productMapper.countProductByCategoryId(id);
	}

	@Override
	public void updateProduct(ProductDO product) {
		if (StringUtils.isNotEmpty(product.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			product.setImage(CommonFileUtils.copyFile(product.getImage(), IConstant.PRODUCT));
		}
		saveProductImages(product);
		saveProductDesc(product);
		this.logger.info("updateProduct=========>" + product);
		productMapper.updateProduct(product);
	}

	@Override
	public List<ProductDO> findProductByExtAttr(String name, String value) {
		return productMapper.findProductByExtAttr(name, value);
	}
}
