package com.shop.api.vo;

import java.util.ArrayList;
import java.util.List;

import com.shop.vo.InfoContentVO;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-13 19:23:04
 */
public class InfoCategoryApiVO {
	private Integer id;
	private String name;
	private String image;
	// 资讯集合
	private List<InfoContentVO> infoContentList = new ArrayList<InfoContentVO>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<InfoContentVO> getInfoContentList() {
		return infoContentList;
	}

	public void setInfoContentList(List<InfoContentVO> infoContentList) {
		this.infoContentList = infoContentList;
	}
}
