package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysNewParkStatisticsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3261595410206011269L;

	private String createTime;
	
	private Integer num;


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
