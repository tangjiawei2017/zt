package com.shop.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.j2ee.WebXmlMappableAttributesRetriever;
import org.springframework.stereotype.Service;

import com.shop.model.CustomFormDO;
import com.shop.service.ICustomFormService;
import com.shop.service.IMenuService;
import com.shop.service.ISiteConfigService;
import com.shop.utils.IConstant;
import com.shop.utils.WebSiteConstant;
import com.shop.vo.MenuItemVO;
import com.shop.vo.SubMenuItemVO;

@SuppressWarnings("all")
@Service(value = "menuService")
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private ISiteConfigService siteConfigService;
	@Autowired
	private ICustomFormService customFormService;
	private Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Override
	public List<MenuItemVO> getMenuItem() {
		return parseMenuXML();
	}

	private List<MenuItemVO> parseMenuXML() {
		Map<String, String> configMap = siteConfigService.selectAllSiteConfigMap();
		List<MenuItemVO> list = new ArrayList<MenuItemVO>();
		SAXReader reader = new SAXReader();
		String xml = WebSiteConstant.HOME + "/webapps" + WebSiteConstant.PROJECT + "/WEB-INF/classes/menu.xml";
		Document document = null;
		try {
			document = reader.read(xml);
		} catch (DocumentException e) {
			this.logger.error(e);
		}
		Element root = document.getRootElement(); // 获取根节点
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			MenuItemVO itemVo = new MenuItemVO();
			itemVo.setName(element.attributeValue("name"));
			String service = element.attributeValue("service");
			String value = configMap.get(service);
			itemVo.setService(service);
			itemVo.setValue(value);
			Iterator<Element> subIterator = element.elementIterator();
			List<SubMenuItemVO> subItemList = new ArrayList<SubMenuItemVO>();
			while (subIterator.hasNext()) {
				Element subElement = subIterator.next();
				SubMenuItemVO subItem = new SubMenuItemVO();
				subItem.setName(subElement.getText());
				String subService = subElement.attributeValue("service");
				String subvalue = configMap.get(subService);
				subItem.setService(subService);
				subItem.setUrl(subElement.attributeValue("url"));
				subItem.setValue(subvalue);
				subItemList.add(subItem);
				itemVo.setSubMenuList(subItemList);
			}
			list.add(itemVo);
		}
		// 判断自定义表单服务是否开启
		String value = configMap.get(IConstant.CUSTOM_FORM_SERVICE);
		if ("1".equals(value)) {
			MenuItemVO itemVo = new MenuItemVO();
			itemVo.setName("自定义表单");
			itemVo.setService(IConstant.CUSTOM_FORM_SERVICE);
			itemVo.setValue("1");
			List<SubMenuItemVO> subItemList = new ArrayList<SubMenuItemVO>();
			List<CustomFormDO> customFormList = customFormService.selectAllCustomForm();
			if (customFormList != null) {
				for (CustomFormDO customForm : customFormList) {
					SubMenuItemVO subItem = new SubMenuItemVO();
					subItem.setName(customForm.getName());
					subItem.setUrl("/customForm/list.action?id=" + customForm.getId());
					subItemList.add(subItem);
				}
			}
			itemVo.setSubMenuList(subItemList);
			list.add(itemVo);
		}
		return list;
	}
}
