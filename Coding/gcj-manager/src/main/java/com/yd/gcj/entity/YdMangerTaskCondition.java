package com.yd.gcj.entity;

import java.util.ArrayList;
import java.util.List;

public class YdMangerTaskCondition {
	private String taskTypeIds;
	private String taskLabelIds;
	private String taskAddr = "-1";
	private Integer taskState = -1;
	private String taskTerm = "-1";
	/**
	 * 获取 taskTypeIds
	 * @return taskTypeIds
	 */
	public String getTaskTypeIds() {
		return taskTypeIds;
	}
	
	public List<Integer> taskTypeIdList(){
		List<Integer> idsList = null;
		if(taskTypeIds!= null && !taskTypeIds.isEmpty() && !taskTypeIds.equals("-1")){
			idsList = new ArrayList<Integer>();
			String[] idsStr = taskTypeIds.split(",");
			for(String idStr : idsStr){
				idsList.add(Integer.parseInt(idStr));
			}
		}
		return idsList;
	}
	
	/**
	 * 设置 taskTypeIds
	 * @param taskTypeIds
	 */
	public void setTaskTypeIds(String taskTypeIds) {
		this.taskTypeIds = taskTypeIds;
	}
	
	/**
	 * 获取 taskLabelIds
	 * @return taskLabelIds
	 */
	public String getTaskLabelIds() {
		return taskLabelIds;
	}
	
	public List<Integer> taskLabelIdList(){
		List<Integer> idsList = null;
		if(taskLabelIds != null && !taskLabelIds.isEmpty() && !taskLabelIds.equals("-1")){
			idsList = new ArrayList<Integer>();
			String[] idsStr = taskLabelIds.split(",");
			for(String idStr : idsStr){
				idsList.add(Integer.parseInt(idStr));
			}
		}
		return idsList;
	}
	
	/**
	 * 设置 taskLabelIds
	 * @param taskLabelIds
	 */
	public void setTaskLabelIds(String taskLabelIds) {
		this.taskLabelIds = taskLabelIds;
	}
	
	/**
	 * 获取 taskAddr
	 * @return taskAddr
	 */
	public String getTaskAddr() {
		return taskAddr;
	}
	
	/**
	 * 设置 taskAddr
	 * @param taskAddr
	 */
	public void setTaskAddr(String taskAddr) {
		this.taskAddr = taskAddr;
	}
	
	/**
	 * 获取 taskState
	 * @return taskState
	 */
	public Integer getTaskState() {
		return taskState;
	}
	
	/**
	 * 设置 taskState
	 * @param taskState
	 */
	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}
	
	/**
	 * 获取 taskTerm
	 * @return taskTerm
	 */
	public String getTaskTerm() {
		return taskTerm;
	}
	
	/**
	 * 设置 taskTerm
	 * @param taskTerm
	 */
	public void setTaskTerm(String taskTerm) {
		this.taskTerm = taskTerm;
	}
	
	
}
