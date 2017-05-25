package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.YdMangerSystemAdmin;
import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.vo.YdMangerMessageVo;
import com.yd.gcj.system.service.YdMangerServiceSystemMessage;
import com.yd.gcj.system.service.YdMangerServiceSystemTask;
import com.yd.gcj.system.service.YdMangerServiceSystemUser;

/**
 * description(消息信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemMessage {

	@Autowired
	private YdMangerServiceSystemMessage ydMangerServiceSystemMessage;

	@Autowired
	private YdMangerServiceSystemUser ydMangerServiceSystemUser;

	@Autowired
	private YdMangerServiceSystemTask ydMangerServiceSystemTask;

	/**
	 * description(查询用户消息信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllMessageUser")
	public String queryAllMessageUser(Integer p, String user_nickname, Integer msg_type, Integer msg_isdel,
			Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_nickname", user_nickname);
		map.put("msg_type", msg_type);
		map.put("msg_isdel", msg_isdel);
		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 8);
		} else {
			PageHelper.startPage(p, 8);
		}
		;
		List<YdMangerMessageVo> messageList = ydMangerServiceSystemMessage.queryAllMessage(map);
		PageInfo<YdMangerMessageVo> pageInfo = new PageInfo<YdMangerMessageVo>(messageList);
		// 总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user_nickname", user_nickname);
		model.addAttribute("msg_type", msg_type);
		model.addAttribute("msg_isdel", msg_isdel);
		model.addAttribute("messageList", messageList);
		return "system/xxgl/usermessage";
	}

	/**
	 * description(查询系统消息信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllMessageAdmin")
	public String queryAllMessageAdmin(Integer p, String admin_name, String user_nickname, Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 5);
		} else {
			PageHelper.startPage(p, 5);
		}
		;

		PageInfo<YdMangerMessageVo> pageInfo;
		Integer msg_sbid;
		if (user_nickname == null) {
			map.put("admin_name", admin_name);
			map.put("user_nickname", user_nickname);
			List<YdMangerMessageVo> messageList = ydMangerServiceSystemMessage.queryAllMessageAdmin(map);
			pageInfo = new PageInfo<YdMangerMessageVo>(messageList);
			model.addAttribute("messageList", messageList);
			model.addAttribute("admin_name", admin_name);
			model.addAttribute("user_nickname", user_nickname);
		} else if (user_nickname.equals("全部")) {
			msg_sbid = 0;
			map.put("msg_sbid", msg_sbid);
			map.put("admin_name", admin_name);
			List<YdMangerMessageVo> messageList = ydMangerServiceSystemMessage.queryAllMessageAdmin(map);
			pageInfo = new PageInfo<YdMangerMessageVo>(messageList);
			model.addAttribute("messageList", messageList);
			model.addAttribute("admin_name", admin_name);
			model.addAttribute("user_nickname", "全部");
		} else {
			map.put("admin_name", admin_name);
			map.put("user_nickname", user_nickname);
			List<YdMangerMessageVo> messageList = ydMangerServiceSystemMessage.queryAllMessageAdmin(map);
			pageInfo = new PageInfo<YdMangerMessageVo>(messageList);
			model.addAttribute("messageList", messageList);
			model.addAttribute("admin_name", admin_name);
			model.addAttribute("user_nickname", user_nickname);
		}

		// 总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 5 != 0) {
			totalPage = total / 5 + 1;
		} else {
			totalPage = total / 5;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		return "system/xxgl/adminmessage";
	}

	/**
	 * description(添加系统消息)
	 * 
	 * @param
	 * @param ydMangerMessage
	 * @return
	 */
	@RequestMapping("/addMessage")
	@ResponseBody
	public Object addMessage(String msg_contents, String msg_sbid, String task_num, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerMessage ydMangerMessage = new YdMangerMessage();
		YdMangerSystemAdmin admin = (YdMangerSystemAdmin) request.getSession().getAttribute("admin");
		Integer admin_id = admin.getAdmin_id();
		if (msg_sbid.equalsIgnoreCase("全部")) {
			msg_sbid = "0";
			Integer pmsg_sbid = Integer.parseInt(msg_sbid);
			ydMangerMessage.setMsg_sbid(pmsg_sbid);
			ydMangerMessage.setMsg_tid(0);
		} else {
			YdMangerUser ydMangerUser = ydMangerServiceSystemUser.queryUserByNickName(msg_sbid);
			msg_sbid = ydMangerUser.getUser_id() + "";
			Integer pmsg_sbid = Integer.parseInt(msg_sbid);
			YdMangerTask ydMangerTask = ydMangerServiceSystemTask.queryTaskByTaskNum(task_num);
			if (ydMangerTask == null) {
				ydMangerMessage.setMsg_tid(0);
			} else {
				ydMangerMessage.setMsg_tid(ydMangerTask.getTask_id());
			}
			ydMangerMessage.setMsg_sbid(pmsg_sbid);
		}

		ydMangerMessage.setMsg_contents(msg_contents);
		ydMangerMessage.setMsg_type(0);
		ydMangerMessage.setMsg_said(admin_id);
		ydMangerMessage.setMsg_isdel(1);

		Integer addNum = ydMangerServiceSystemMessage.addMessage(ydMangerMessage);
		if (addNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(根据指定的id修改消息)
	 * 
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public Object deleteMessage(Integer msg_id) {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer deleteNum = ydMangerServiceSystemMessage.deleteMessageByMsgId(msg_id);
		if (deleteNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@RequestMapping("/updateAdminMessageByMsgId/{msg_id}")
	public String queryAdminMessageByMsgId(@PathVariable Integer msg_id, Model model) {
		YdMangerMessageVo ydMangerMessageVo = ydMangerServiceSystemMessage.queryMesssageByMsgId(msg_id);
		model.addAttribute("ydMangerMessageVo", ydMangerMessageVo);
		return "system/xxgl/adminmessageupdate";
	}

	/**
	 * description(根据指定的msg_id修改系统消息)
	 * 
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	@RequestMapping("/updateAdminMessage")
	@ResponseBody
	public Object updateAdminMessageByMsgId(YdMangerMessageVo ydMangerMessageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemMessage.updateAdminMessageByMsgId(ydMangerMessageVo);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

}
