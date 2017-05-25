package com.yd.gcj.entity.vo;

import javax.servlet.http.HttpSession;

public class PageNum {
	/**当前页码*/
	private Integer pageIndex = 1;
	/**显示数据条数*/
	private Integer pageNum = 10;
	/**数据总条数*/
	private Integer pageCount = 0;
	/**最大页码*/
	private Integer maxIndex = 1;
	/**从第几条开始查询*/
	private Integer startPageNum;
	/**数据*/
	private Object data;
	
	public PageNum(){
		
	}
	public PageNum(HttpSession session,Integer countNum,String sessionName){
		try {
			//获取session中的当前页面
			Integer nowPageNum = (Integer) session.getAttribute(sessionName);
			//检查session中是否有分页存储
			if(nowPageNum != null && nowPageNum >= 0){
				pageIndex = nowPageNum;
			}
			pageCount = countNum;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取 当前页码
	 * @return pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * 设置 当前页码
	 * @param pageIndex
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	/**
	 * 获取 显示数据条数
	 * @return pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}
	

	/**
	 * 设置 显示数据条数
	 * @param pageNum
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	

	/**
	 * 获取 数据总条数
	 * @return pageCount
	 */
	public Integer getPageCount() {
		
		return pageCount;
	}
	
	/**
	 * 设置 数据总条数
	 * @param pageCount
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		if(pageCount>0){
			maxIndex = pageCount%pageNum>0?(pageCount/pageNum)+1:pageCount/pageNum;
		}
	}
	
	/**
	 * 获取 最大页码
	 * @return maxIndex
	 */
	public Integer getMaxIndex() {
		if(pageCount>0){
			maxIndex = pageCount%pageNum>0?(pageCount/pageNum)+1:pageCount/pageNum;
		}
		return maxIndex;
	}
	
	/**
	 * 设置 最大页码
	 * @param maxIndex
	 */
	public void setMaxIndex(Integer maxIndex) {
		this.maxIndex = maxIndex;
	}

	/**
	 * 获取 从第几条开始查询
	 * @return startPageNum
	 */
	public Integer getStartPageNum() {
		startPageNum = (pageIndex-1)*pageNum;
		return startPageNum;
	}

	/**
	 * 获取 data
	 * @return data
	 */
	public Object getData() {
		return data;
	}
	

	/**
	 * 设置 data
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
}
