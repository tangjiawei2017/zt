package com.shop.model;

import java.util.Date;

/**
 * 在线留言
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-02 21:20:39
 */
public class MessageDO {
	private Integer id;
	// 用户名
	private String account;
	private String phone;
	private String email;
	private String content;
	// 创建时间
	private Date ctime;
	// 状态 0为未回复,1为已回复
	private Integer status;
	// 回复内容
	private String answer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "MessageDO [id=" + id + ", account=" + account + ", phone=" + phone + ", email=" + email + ", content="
				+ content + ", ctime=" + ctime + ", status=" + status + ", answer=" + answer + "]";
	}
}
