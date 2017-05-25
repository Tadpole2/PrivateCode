package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskLabel;
import com.yd.gcj.mapper.YdMangerMapperTaskLabel;
import com.yd.gcj.service.YdMangerServiceTaskLabel;
@Service("YdMangerServiceTaskLabelTR")
public class YdMangerServiceImplTaskLabelTR implements YdMangerServiceTaskLabel{
	
	@Autowired
	private YdMangerMapperTaskLabel ydMangerMapperTaskLabel;
	
	@Override	
	public List<YdMangerTaskLabel> $queryByTid(Integer tasktr_tid) {
		
		return ydMangerMapperTaskLabel.$queryByTid(tasktr_tid);
	}

	@Override
	public YdMangerTaskLabel $queryById(Integer tasktr_id) {
		
		return ydMangerMapperTaskLabel.$queryById(tasktr_id);
	}

	@Override
	public Integer $delete(Integer taskId) {
		return ydMangerMapperTaskLabel.$deleteByTaskId(taskId);
	}
	
}
