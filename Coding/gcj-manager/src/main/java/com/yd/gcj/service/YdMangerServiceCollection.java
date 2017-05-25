package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerCollection;

public interface YdMangerServiceCollection {

	/***
	 * 查询该用户所有收藏任务信息
	 * @param colle_uid
	 * @return
	 */
	Object $queryAll(HashMap<String, Object> map);
	
	/***
	 * 查询该用户根据分页的收藏任务信息
	 * @param colle_uid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerCollection> $queryAllByPageNum(Integer userId,Integer startPageNum,Integer queryPageNum);
	
	/***
	 * 查询该用户收藏任务信息数量
	 * @param colle_uid
	 * @return
	 */
	Object $queryCountNum(HashMap<String, Object> map);
	
	/***
	 * 添加收藏
	 * @param colle
	 * @return
	 */
	@Transactional
	Object $insert(Integer userId,Integer taskId);
	
	/***
	 * 删除收藏任务信息（取消收藏）
	 * @param colle_id
	 * @return
	 */
	@Transactional
	Object $delete(Integer userId,Integer taskId);
}
