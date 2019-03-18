package com.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.SitePicMapper;
import com.shop.model.SitePicDO;
import com.shop.service.ISitePicService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-03 02:24:51
 */
@Service
public class SitePicServiceImpl implements ISitePicService {

	@Autowired
	private SitePicMapper sitePicMapper;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void addSitePic(SitePicDO sitePic) {
		if (StringUtils.isNotEmpty(sitePic.getPath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			sitePic.setPath(CommonFileUtils.copyFile(sitePic.getPath(), IConstant.GALLERY));
		}
		if (StringUtils.isNotEmpty(sitePic.getMpath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			sitePic.setMpath(CommonFileUtils.copyFile(sitePic.getMpath(), IConstant.GALLERY));
		}
		sitePicMapper.addSitePic(sitePic);
	}

	@Override
	public List<SitePicDO> findAllSitePic() {
		return sitePicMapper.findAllSitePic();
	}

	@Override
	public Integer findSitePicByPath(String path) {
		return sitePicMapper.findSitePicByPath(path);
	}

	@Override
	public SitePicDO findSitePicById(Integer id) {
		return sitePicMapper.findSitePicById(id);
	}

	@Override
	public void updateSitePic(SitePicDO sitePic) {
		if (StringUtils.isNotEmpty(sitePic.getPath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			sitePic.setPath(CommonFileUtils.copyFile(sitePic.getPath(), IConstant.GALLERY));
		}
		sitePicMapper.updateSitePic(sitePic);
	}

	@Override
	public void delSitePic(Integer id) {
		sitePicMapper.delSitePic(id);
	}

	@Override
	public void saveSitePic(String data) {
		JSONArray dataArray = JSONArray.fromObject(data);
		if (dataArray != null && dataArray.size() > 0) {
			for (int i = 0; i < dataArray.size(); i++) {
				SitePicDO sitePic = new SitePicDO();
				JSONObject dataObj = dataArray.getJSONObject(i);
				if (dataObj.has("id")) {
					sitePic.setId(dataObj.getInt("id"));
				}
				if (dataObj.has("path")) {
					sitePic.setPath(dataObj.getString("path"));
				}
				if (dataObj.has("link")) {
					sitePic.setLink(dataObj.getString("link"));
				}
				if (dataObj.has("number")) {
					sitePic.setNumber(dataObj.getInt("number"));
				}
				this.logger.info("sitePic========>" + sitePic);
				if (sitePic.getId() == null) {
					this.addSitePic(sitePic);
				} else {
					this.updateSitePic(sitePic);
				}
			}
		}
	}
}
