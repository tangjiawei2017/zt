package com.shop.appservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.appservice.ICustomFormAppService;
import com.shop.model.CustomFormDO;
import com.shop.model.CustomFormItemDO;
import com.shop.service.ICustomFormItemService;
import com.shop.service.ICustomFormService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CustomFormAppServiceImpl implements ICustomFormAppService {

	@Autowired
	private ICustomFormService customFormService;
	@Autowired
	private ICustomFormItemService customFormItemService;

	private Logger logger = Logger.getLogger(CustomFormAppServiceImpl.class);

	@Override
	public void addCustomForm(String data) {
		JSONObject jdata = JSONObject.fromObject(data);
		CustomFormDO customForm = new CustomFormDO();
		String id = UUID.randomUUID().toString();
		customForm.setId(id);
		customForm.setCtime(new Date());
		logger.info("data========>" + data);
		logger.info("customFormName=======>" + jdata.getString("customFormName"));
		if (jdata.has("customFormName")) {
			customForm.setName(jdata.getString("customFormName"));
		}
		if (jdata.has("customFormItemArray")) {
			JSONArray jarray = jdata.getJSONArray("customFormItemArray");
			for (int i = 0; i < jarray.size(); i++) {
				JSONObject jobj = jarray.getJSONObject(i);
				CustomFormItemDO customFormItem = new CustomFormItemDO();
				customFormItem.setFromId(id);
				customFormItem.setName(jobj.getString("name"));
				customFormItem.setItemName(jobj.getString("itemName"));
				customFormItem.setType(jobj.getInt("type"));
				customFormItem.setDataType(jobj.getInt("dataType"));
				customFormItem.setDataValue(jobj.getString("value"));
				customFormItem.setItemPrompt(jobj.getString("itemPrompt"));
				customFormItem.setIsRequried(jobj.getBoolean("isRequired"));
				customFormItem.setIsItem(jobj.getBoolean("isItem"));
				customFormItemService.insertCustomFormItem(customFormItem);
			}
		}
		// 插入表单
		customFormService.insertCustomForm(customForm);
	}

	@Override
	public void updateCustomForm(String data) {
		JSONObject jdata = JSONObject.fromObject(data);
		logger.info("data========>" + data);
		CustomFormDO customForm = new CustomFormDO();
		String customFormId = jdata.getString("customFormId");
		customForm.setId(customFormId);
		if (jdata.has("customFormName")) {
			customForm.setName(jdata.getString("customFormName"));
		}
		/*
		 * 自定义表单的更新,应该包含 表单项的新增、更新、删除操作
		 */
		List<CustomFormItemDO> customFormItemList = customFormItemService.selectCustomFormItemByFormId(customFormId);
		List<Integer> customFormItemIdsList = new ArrayList<Integer>();
		if (jdata.has("customFormItemArray")) {
			JSONArray jarray = jdata.getJSONArray("customFormItemArray");
			for (int i = 0; i < jarray.size(); i++) {
				JSONObject jobj = jarray.getJSONObject(i);
				CustomFormItemDO customFormItem = new CustomFormItemDO();
				String id = jobj.getString("id");
				customFormItem.setName(jobj.getString("name"));
				customFormItem.setItemName(jobj.getString("itemName"));
				customFormItem.setFromId(customFormId);
				customFormItem.setType(jobj.getInt("type"));
				customFormItem.setDataType(jobj.getInt("dataType"));
				customFormItem.setDataValue(jobj.getString("value"));
				customFormItem.setItemPrompt(jobj.getString("itemPrompt"));
				customFormItem.setIsRequried(jobj.getBoolean("isRequired"));
				customFormItem.setIsItem(jobj.getBoolean("isItem"));
				if (StringUtils.isEmpty(id)) {
					customFormItemService.insertCustomFormItem(customFormItem);
				} else {
					customFormItem.setId(Integer.valueOf(id));
					customFormItemIdsList.add(Integer.valueOf(id));
					customFormItemService.updateCustomFormItemDOByPrimaryKeySelective(customFormItem);
				}
				this.logger.info("customFormitem======>" + customFormItem);
			}
		}
		// 不包含则删除
		for (CustomFormItemDO item : customFormItemList) {
			if (!customFormItemIdsList.contains(item.getId())) {
				customFormItemService.deleteCustomFormItemByPrimaryKey(item.getId());
			}
		}
		customFormService.updateCustomFormByPrimaryKeySelective(customForm);
	}

	@Override
	public void delCustomForm(String id) {
		// 删除表单项
		customFormItemService.deleteCustomFormItemByFormId(id);
		// 删除表单
		customFormService.deleteCustomFormByPrimaryKey(id);
	}

}
