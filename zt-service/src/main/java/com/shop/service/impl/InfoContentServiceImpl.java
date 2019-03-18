package com.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.InfoContentMapper;
import com.shop.model.InfoContentDO;
import com.shop.service.IInfoContentService;

@Service
public class InfoContentServiceImpl implements IInfoContentService {
	@Autowired
	private InfoContentMapper infoContentMapper;

	@Override
	public int deleteInfoContentByPrimaryKey(Integer id) {
		return infoContentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertInfoContent(InfoContentDO record) {
		return infoContentMapper.insert(record);
	}

	@Override
	public int insertInfoContentSelective(InfoContentDO record) {
		return infoContentMapper.insertSelective(record);
	}

	@Override
	public InfoContentDO selectInfoContentByPrimaryKey(Integer id) {
		return infoContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateInfoContentByPrimaryKeySelective(InfoContentDO record) {
		return infoContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateInfoContentByPrimaryKeyWithBLOBs(InfoContentDO record) {
		return infoContentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateInfoContentByPrimaryKey(InfoContentDO record) {
		return infoContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public int countInfoContent() {
		return infoContentMapper.countInfoContent();
	}

	@Override
	public List<InfoContentDO> selectInfoContentByPage(Integer currPage, Integer limit) {
		int start = (currPage - 1) * limit;
		return infoContentMapper.selectInfoContentByPage(start, limit);
	}

	@Override
	public void batchDelInfoContent(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] array = ids.split(",");
			for (String id : array) {
				infoContentMapper.deleteByPrimaryKey(Integer.valueOf(id));
			}
		}
	}

	@Override
	public InfoContentDO selectInfoContentAndCategoryByPrimaryKey(Integer id) {
		return infoContentMapper.selectInfoContentAndCategoryByPrimaryKey(id);
	}

	@Override
	public Integer countInfoContentByCategoryId(Integer id) {
		return infoContentMapper.countInfoContentByCategoryId(id);
	}

	@Override
	public List<InfoContentDO> selectInfoContentByPageAndCategory(Integer currPage, Integer limit, Integer category) {
		int start = (currPage - 1) * limit;
		return infoContentMapper.selectInfoContentByPageAndCategory(start, limit, category);
	}

	@Override
	public InfoContentDO getPreInfoContent(Integer cid, Date ctime) {
		List<InfoContentDO> list = this.infoContentMapper.getPreInfoContent(cid, ctime);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public InfoContentDO getNextInfoContent(Integer cid, Date ctime) {
		List<InfoContentDO> list = this.infoContentMapper.getNextInfoContent(cid, ctime);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<InfoContentDO> getNewInfo(Integer limit) {
		return this.infoContentMapper.getNewInfo(limit);
	}

}
