package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskMsg;

public interface YdMangerServiceTaskMsg {

	/***
	 * 根据任务id查询该任务所发布的公告
	 * @param map
	 * @return
	 */
	List<YdMangerTaskMsg> $queryByTid(Integer taskId);
	
	/***
	 * 根据公告id查询该公告信息
	 * @param map
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 添加新的公告信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerTaskMsg taskMsg);
	
	/***
	 * 修改公告信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 删除指定id公告
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
}
