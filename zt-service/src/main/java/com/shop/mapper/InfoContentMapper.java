package com.shop.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.InfoContentDO;

public interface InfoContentMapper {
	int countInfoContent();

	int deleteByPrimaryKey(Integer id);

	int insert(InfoContentDO record);

	List<InfoContentDO> selectInfoContentByPage(@Param("start") Integer start, @Param("limit") Integer limit);

	List<InfoContentDO> selectInfoContentByPageAndCategory(@Param("start") Integer start, @Param("limit") Integer limit,
			@Param("categoryId") Integer categoryId);

	List<InfoContentDO> getPreInfoContent(@Param("cid") Integer cid, @Param("created") Date created);

	List<InfoContentDO> getNextInfoContent(@Param("cid") Integer cid, @Param("created") Date created);

	List<InfoContentDO> getNewInfo(Integer limit);

	InfoContentDO selectInfoContentAndCategoryByPrimaryKey(Integer id);

	Integer countInfoContentByCategoryId(Integer id);

	int insertSelective(InfoContentDO record);

	InfoContentDO selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(InfoContentDO record);

	int updateByPrimaryKeyWithBLOBs(InfoContentDO record);

	int updateByPrimaryKey(InfoContentDO record);
}