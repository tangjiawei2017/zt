package com.shop.service;

import java.util.Date;
import java.util.List;

import com.shop.model.InfoContentDO;

public interface IInfoContentService {

	int countInfoContent();

	int deleteInfoContentByPrimaryKey(Integer id);

	int insertInfoContent(InfoContentDO record);

	Integer countInfoContentByCategoryId(Integer id);

	int insertInfoContentSelective(InfoContentDO record);

	InfoContentDO selectInfoContentByPrimaryKey(Integer id);
	
	List<InfoContentDO> getNewInfo(Integer limit);

	InfoContentDO getPreInfoContent(Integer cid, Date ctime);

	InfoContentDO getNextInfoContent(Integer cid, Date ctime);

	List<InfoContentDO> selectInfoContentByPage(Integer currPage, Integer limit);

	public List<InfoContentDO> selectInfoContentByPageAndCategory(Integer currPage, Integer limit, Integer category);

	int updateInfoContentByPrimaryKeySelective(InfoContentDO record);

	int updateInfoContentByPrimaryKeyWithBLOBs(InfoContentDO record);

	int updateInfoContentByPrimaryKey(InfoContentDO record);

	InfoContentDO selectInfoContentAndCategoryByPrimaryKey(Integer id);

	void batchDelInfoContent(String ids);
}
