package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.GalleryMapper;
import com.shop.model.GalleryDO;
import com.shop.service.IGalleryService;

@Service
public class GalleryServiceImpl implements IGalleryService {
	@Autowired
	private GalleryMapper galleryMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return galleryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GalleryDO record) {
		return galleryMapper.insert(record);
	}

	@Override
	public int insertSelective(GalleryDO record) {
		return galleryMapper.insertSelective(record);
	}

	@Override
	public GalleryDO selectByPrimaryKey(String id) {
		return galleryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GalleryDO record) {
		return galleryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GalleryDO record) {
		return galleryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<GalleryDO> selectAll() {
		return galleryMapper.selectAll();
	}

}
