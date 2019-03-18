package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.CustomerDO;
import com.shop.service.ICustomerService;
import com.shop.utils.CustomerConvert;
import com.shop.vo.CustomerCondition;
import com.shop.vo.CustomerVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/list.action")
	public String list() {

		return "customer_list";
	}

	/**
	 * @param currpage
	 * @param limit
	 * @param condition
	 * @param response
	 */
	@RequestMapping("/customerListAjax.action")
	public void customerListAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, CustomerCondition condition,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject result = new JSONObject();
		Integer code = 0, count = 0;
		String msg = "success";
		JSONArray data = new JSONArray();
		try {
			count = customerService.countCustomer(condition);
			List<CustomerDO> customerList = customerService.findCustomersByCondition(currpage, limit, condition);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (customerList != null) {
				for (CustomerDO customer : customerList) {
					JSONObject temp = new JSONObject();
					temp.put("id", customer.getId());
					temp.put("account", customer.getAccount());
					temp.put("name", customer.getName());
					temp.put("regtime", format.format(customer.getRegtime()));
					temp.put("source", customer.getSource());
					temp.put("status", customer.getStatus());
					data.add(temp);
				}
			}
		} catch (Exception e) {
			this.logger.error(e);
			code = 1;
			count = 0;
			msg = "fail";
		}
		result.put("code", code);
		result.put("msg", msg);
		result.put("count", count);
		result.put("data", data);
		try {
			response.getWriter().print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("preAddCustomer.action")
	public String preAddCustomer() {
		return "customer_add";
	}

	/**
	 * 添加会员
	 * 
	 * @param customer
	 * @param out
	 */
	@RequestMapping("addCustomer.action")
	public void addCustomer(CustomerDO customer, PrintWriter out) {
		JSONObject result = new JSONObject();
		try {
			// 来源 后台添加
			customer.setSource(1);
			customerService.addCustomer(customer);
			result.put("result", "success");
		} catch (Exception e) {
			this.logger.error(e);
			result.put("result", "fail");
		}
		out.print(result.toString());
	}

	/**
	 * 批量删除会员
	 * 
	 * @param ids
	 * @param out
	 */
	@RequestMapping("batchDelCustomer.action")
	public void batchDelCustomer(@RequestParam(value = "ids", required = true) String ids, PrintWriter out) {
		JSONObject result = new JSONObject();
		try {
			this.customerService.batchDelCustomer(ids);
			result.put("result", "success");
		} catch (Exception e) {
			this.logger.error(e);
			result.put("result", "fail");
		}
		out.print(result.toString());
	}

	/**
	 * 批量启用会员
	 * 
	 * @param ids
	 * @param out
	 */
	@RequestMapping("batchPutOnCustomer.action")
	public void batchPutOnCustomer(@RequestParam(value = "ids", required = true) String ids, PrintWriter out) {
		JSONObject result = new JSONObject();
		try {
			this.customerService.batchUpdateCustomerStatus(ids, 1);
			result.put("result", "success");
		} catch (Exception e) {
			this.logger.error(e);
			result.put("result", "fail");
		}
		out.print(result.toString());
	}

	/**
	 * 批量停用会员
	 * 
	 * @param ids
	 * @param out
	 */
	@RequestMapping("batchPutOffCustomer.action")
	public void batchPutOffCustomer(@RequestParam(value = "ids", required = true) String ids, PrintWriter out) {
		JSONObject result = new JSONObject();
		try {
			this.customerService.batchUpdateCustomerStatus(ids, 0);
			result.put("result", "success");
		} catch (Exception e) {
			this.logger.error(e);
			result.put("result", "fail");
		}
		out.print(result.toString());
	}

	/**
	 * 更新会员状态
	 * 
	 * @param id
	 * @param status
	 * @param out
	 */
	@RequestMapping("/updateCustomerStatus.action")
	public void updateCustomerStatus(Integer id, Integer status, PrintWriter out) {
		customerService.updateCustomerStatus(id, status);
		out.print("success");
	}

	/**
	 * 删除会员
	 * 
	 * @param id
	 */
	@RequestMapping("/delCustomer.action")
	public void delCustomer(Integer id, PrintWriter out) {
		JSONObject result = new JSONObject();
		try {
			customerService.delCustomer(id);
			result.put("result", "success");
		} catch (Exception e) {
			this.logger.error(e);
			result.put("result", "fail");
		}
		out.print(result.toString());
	}

	@RequestMapping("/checkCustomerAccountExist.action")
	public void checkCustomerAccountExist(String account, PrintWriter out) {
		JSONObject result = new JSONObject();
		Integer count = customerService.checkCustomerAccountExist(account);
		if (count.intValue() > 0) {
			result.put("result", "fail");
		} else {
			result.put("result", "success");
		}
		out.print(result.toString());
	}

	@RequestMapping("/checkCustomerPhoneExist.action")
	public void checkCustomerPhoneExist(String cellphone, PrintWriter out) {
		JSONObject result = new JSONObject();
		Integer count = customerService.checkCustomerPhoneExist(cellphone);
		if (count.intValue() > 0) {
			result.put("result", "fail");
		} else {
			result.put("result", "success");
		}
		out.print(result.toString());
	}

	@RequestMapping("/customerDetail.action")
	public ModelAndView customerDetail(Integer id) {
		ModelAndView mv = new ModelAndView();
		CustomerDO customerDO = customerService.findCustomerById(id);
		CustomerVO customer = CustomerConvert.convertCustomerDOToVO(customerDO);
		// 保留两位小数
		mv.addObject("customer", customer);
		mv.setViewName("customer_detail");
		return mv;
	}

	@RequestMapping("/customerOrderListAjax.action")
	public void customerOrderListAjax(Integer payerId, @RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject result = new JSONObject();
		result.put("code", 0);
		result.put("msg", "success");
		result.put("count", 0);
		result.put("data", null);
		try {
			response.getWriter().print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
