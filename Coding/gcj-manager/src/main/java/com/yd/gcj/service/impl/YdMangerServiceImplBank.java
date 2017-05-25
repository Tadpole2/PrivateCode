package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerBank;
import com.yd.gcj.mapper.YdMangerMapperBank;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.service.YdMangerServiceBank;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceBank")
public class YdMangerServiceImplBank implements YdMangerServiceBank{
	
	@Autowired
	private YdMangerMapperBank mapperBank;
	
	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer bank_uid = (Integer) map.get("bank_uid");
			mapInitFactory.setData(mapperBank.$queryAll(bank_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		return map;
	}


	@Override
	public Object $queryCountNum(HashMap<String, Object> map) {
		return map;
	}

	@Override
	public Object $addBankMsg(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer bank_uid = (Integer) map.get("bank_uid");
		Integer isExist = mapperUser.$userIsExistById(bank_uid);
		if(isExist>0){
			MapFactory.removeByKeys(map, "bank_create_time","bank_update_time");
			YdMangerBank bank = (YdMangerBank) MapFactory.toObject(map, YdMangerBank.class);
			Date date = new Date();
			bank.setBank_created_time(date);
			bank.setBank_updated_time(date);
			Integer success = mapperBank.$insert(bank);
			if(success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功");
			}else{
				mapInitFactory.setMsg("502", "添加失败");
			}
		}else{
			mapInitFactory.setMsg("501", "参数有误");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delBankMsg(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer bank_id = (Integer) map.get("bank_id");
		Integer bank_uid = (Integer) map.get("bank_uid");
		Integer success = mapperBank.$delBankMsg(bank_id, bank_uid);
		if(success>0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "删除成功");
		}else{
			mapInitFactory.setMsg("501", "删除失败");
		}
		return mapInitFactory.getMap();
	}
	
}
