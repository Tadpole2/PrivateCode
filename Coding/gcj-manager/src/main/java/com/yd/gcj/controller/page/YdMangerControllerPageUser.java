package com.yd.gcj.controller.page;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerMsgSize;
import com.yd.gcj.entity.YdMangerPhoneCode;
import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceUser;
import com.yd.gcj.service.YdMangerServiceUserLabel;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/user", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageUser {

	@Autowired
	private YdMangerServiceUser ydMangerServiceUser;

	@Autowired
	private YdMangerServiceUserLabel ydMangerServiceUserLabel;

	/***
	 * 注册
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public Object regist(String userPhone, String phoneCode, String userPwd, int userType, HttpSession session) {
		try {
			MapInitFactory mapInitFactory = new MapInitFactory();
			try {
				YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute("phoneCodeMsg");

				if (pc != null) {
					if (pc.getPhone().equals(userPhone)) {
						if (pc.getCode().equals(phoneCode)) {
							if (new Date().getTime() - pc.getTime() < 600000) {
								HashMap<String, Object> map = new HashMap<>();
								YdMangerUserVo userVo = new YdMangerUserVo();
								userVo.setUser_phone(userPhone);
								userVo.setPhoneCode(phoneCode);
								userVo.setUser_pwd(userPwd);
								userVo.setUser_type(userType);
								map.put("registEntity", userVo);
								map.put("session", session);
								session.setAttribute("msgSize", new YdMangerMsgSize());
								return ydMangerServiceUser.$regist(map);
							} else {
								mapInitFactory.setMsg("504", "验证码已过期，请重新发送！");
							}
						} else {
							mapInitFactory.setMsg("503", "验证码不正确！");
						}
					} else {
						mapInitFactory.setMsg("502", "接收验证码手机号与注册手机号不匹配！");
					}
				} else {
					mapInitFactory.setMsg("501", "发送验证码后再进行注册！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapInitFactory.setSystemError();
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 登陆
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(String userPhone, String userPwd, Integer userType, Integer userIsAutoLogin,HttpSession session,HttpServletResponse response) {
		try {
			HashMap<String, Object> map = new HashMap<>();
			YdMangerUser user = new YdMangerUser();
			user.setUser_phone(userPhone);
			user.setUser_pwd(userPwd);
			user.setUser_type(userType);
			map.put("user", user);
			map.put("session", session);
			MapInitFactory mapInitFactory = ydMangerServiceUser.$login(map);
			String code = (String) mapInitFactory.getMap().get("code");
			if("200".equals(code) && userIsAutoLogin == 1){
				Cookie cookie = new Cookie("userIsAutoLogin", userPhone);
				cookie.setMaxAge(604800000);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 设置用户昵称
	 * 
	 * @param userId
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/updateNickname", method = RequestMethod.POST)
	public Object updateNickname(Integer userId, String nickname) {
		try {
			return ydMangerServiceUser.$updateNickname(userId, nickname);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 设置用户姓名
	 * 
	 * @param userId
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/verified", method = RequestMethod.POST)
	public Object verified(Integer userId, String userName, String userPhone, String code, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				if (userPhone != null && userPhone.equals(userVo.getUser_phone())) {
					return ydMangerServiceUser.$updateUserName(userId, userName);
				} else {
					return new MapInitFactory("501", "参数有误！").getMap();
				}
			} else {
				return new MapInitFactory("600", "没有登录或者登录超时，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 设置用户所在地
	 * 
	 * @param userId
	 * @param userAddr
	 * @return
	 */
	@RequestMapping(value = "/updateUserAddr", method = RequestMethod.POST)
	public Object updateUserAddr(Integer userId, String userAddr) {
		try {
			return ydMangerServiceUser.$updateAddr(userId, userAddr);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 设置用户简历信息
	 * 
	 * @param userId
	 * @param userResume
	 * @return
	 */
	@RequestMapping(value = "/updateUserResume", method = RequestMethod.POST)
	public Object updateUserResume(Integer userId, String userResume) {
		try {
			return ydMangerServiceUser.$updateUserResume(userId, userResume);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 修改用户邮箱
	 * 
	 * @param userId
	 * @param userEmail
	 * @return
	 */
	@RequestMapping(value = "/updateUserEmail", method = RequestMethod.POST)
	public Object updateUserEmail(Integer userId, String userEmail) {
		try {
			return ydMangerServiceUser.$updateUserEmail(userId, userEmail);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 修改用户QQ号码
	 * 
	 * @param userId
	 * @param userQQ
	 * @return
	 */
	@RequestMapping(value = "/updateUserQQ", method = RequestMethod.POST)
	public Object updateUserQQ(Integer userId, String userQQ) {
		try {
			return ydMangerServiceUser.$updateUserQQ(userId, userQQ);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 用户技能认证（后期需要移植到认证模块）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/labelCFA", method = RequestMethod.POST)
	public Object labelCFA(@RequestBody HashMap<String, Object> map) {
		try {
			return ydMangerServiceUser.$labelCFA(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 用户认证企业信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/enterCFA", method = RequestMethod.POST)
	public Object enterCFA(String userEname, String emprs, Integer state, Integer isResid, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				return ydMangerServiceUser.$enterCFA(userVo.getUser_id(), userEname, emprs, state, isResid);
			} else {
				return new MapInitFactory("600", "登陆超时，请重新登陆！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 更新接单类型
	 * 
	 * @param userId
	 * @param otypeNum
	 * @return
	 */
	@RequestMapping(value = "/updateOtype", method = RequestMethod.POST)
	public Object updateOtype(Integer userId, Integer otypeNum, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				return ydMangerServiceUser.$updateOtype(userId, otypeNum);
			} else {
				return new MapInitFactory().setMsg("506", "登录超时，请重新登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 修改登录密码
	 * 
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/updateLoginPwd", method = RequestMethod.POST)
	public Object updateLoginPwd(Integer userId, String oldPwd, String newPwd, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null && userVo.getUser_id() == userId) {
				return ydMangerServiceUser.$updatePwd(userId, oldPwd, newPwd);
			} else {
				return new MapInitFactory().setMsg("503", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 修改支付密码
	 * 
	 * @param userId
	 * @param phoneNum
	 * @param phoneCode
	 * @param payPwd
	 * @return
	 */
	@RequestMapping(value = "/updatePayPwd", method = RequestMethod.POST)
	public Object updatePayPwd(Integer userId, String phoneNum, String phoneCode, String payPwd,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
			if (userVo != null) {
				if (userVo.getUser_id() == userId && phoneNum.equals(userVo.getUser_phone())) {
					MapInitFactory mapInitFactory = new MapInitFactory();
					YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute("xgjymm");
					if (pc != null) {
						if (pc.getPhone().equals(phoneNum)) {
							if (pc.getCode().equals(phoneCode)) {
								if (new Date().getTime() - pc.getTime() < 600000) {
									return ydMangerServiceUser.$updatePPwd(userId, phoneNum, payPwd);
								} else {
									mapInitFactory.setMsg("504", "验证码已过期，请重新发送！");
								}
							} else {
								mapInitFactory.setMsg("503", "验证码不正确！");
							}
						} else {
							mapInitFactory.setMsg("502", "接收验证码手机号与提交的手机号不匹配！");
						}
					} else {
						mapInitFactory.setMsg("501", "发送验证码后再进行操作！");
					}
					return mapInitFactory.getMap();
				} else {
					return new MapInitFactory().setMsg("504", "参数有误！").getMap();
				}
			} else {
				return new MapInitFactory().setMsg("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 修改用户手机号
	 * 
	 * @param userId
	 * @param phoneCode
	 * @param oldPhoneNum
	 * @param newPhoneNum
	 * @return
	 */
	@RequestMapping(value = "/updateUserPhone", method = RequestMethod.POST)
	public Object updateUserPhone(Integer userId, String phoneCode, String oldPhoneNum, String newPhoneNum,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
			MapInitFactory mapInitFactory = new MapInitFactory();
			if (userVo != null) {
				if (userVo.getUser_id() == userId && oldPhoneNum.equals(userVo.getUser_phone())) {
					YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute("wjmm");
					if (pc != null) {
						if (pc.getPhone().equals(newPhoneNum)) {
							if (pc.getCode().equals(phoneCode)) {
								if (new Date().getTime() - pc.getTime() < 600000) {
									return ydMangerServiceUser.$updateUserPhone(userId, oldPhoneNum, newPhoneNum);
								} else {
									mapInitFactory.setMsg("504", "验证码已过期，请重新发送！");
								}
							} else {
								mapInitFactory.setMsg("503", "验证码不正确！");
							}
						} else {
							mapInitFactory.setMsg("502", "接收验证码手机号与提交的机号不匹配！");
						}
					} else {
						mapInitFactory.setMsg("501", "发送验证码后再进行操作！");
					}

				} else {
					mapInitFactory.setMsg("504", "参数有误！");
				}
			} else {
				mapInitFactory.setMsg("600", "登录超时或者没有登录，请登录后再进行操作！");
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 服务商技能认证
	 * 
	 * @param labelIds
	 * @param reLabel
	 * @return
	 */
	@RequestMapping(value = "/userLabels", method = RequestMethod.POST)
	public Object addUserLabels(String labelIds, HttpServletRequest request) {
		try {
			final YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				int state = userVo.getUser_skillstate();
				if(state == 3){
					return new MapInitFactory("502","认证信息审核中,无需重复提交申请!").getMap();
				}else if(state == 1){
					return new MapInitFactory("503","专业认证成功,无需重复提交申请!").getMap();
				}else{
					Object object = ydMangerServiceUserLabel.$insert(userVo.getUser_id(), labelIds,userVo);
					request.getSession().setAttribute("user", userVo);
					return object;
				}
			} else {
				return new MapInitFactory().setMsg("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 修改密码
	 * 
	 * @param userPhone
	 * @param userPhoneCode
	 * @param userPwd
	 * @return
	 */
	@RequestMapping(value = "/forget", method = RequestMethod.POST)
	public Object forgetPwd(String userPhone, String userPhoneCode, String userPwd, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerPhoneCode pc = (YdMangerPhoneCode) request.getSession().getAttribute("wjmm");
			if (pc != null) {
				if (pc.getPhone().equals(userPhone)) {
					if (pc.getCode().equals(userPhoneCode)) {
						if (new Date().getTime() - pc.getTime() < 600000) {
							return ydMangerServiceUser.$forgetPwd(userPhone, userPwd);
						} else {
							mapInitFactory.setMsg("504", "验证码已过期，请重新发送！");
						}
					} else {
						mapInitFactory.setMsg("503", "验证码不正确！");
					}
				} else {
					mapInitFactory.setMsg("502", "接收验证码手机号与提交的手机号不匹配！");
				}
			} else {
				mapInitFactory.setMsg("501", "发送验证码后再进行操作！");
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return mapInitFactory.setSystemError().getMap();
		}
	}

	/***
	 * 服务商职称认证
	 * 
	 * @param jobTitle
	 * @return
	 */
	@RequestMapping("jobTitleVerified")
	public Object jobTitleVerified(Integer level, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			final YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				int state = userVo.getUser_jobtitle();
				if(state == 3){
					mapInitFactory.setMsg("501", "认证审核中,无需重复提交!");	
				}else if(state == 1){
					mapInitFactory.setMsg("502", "您已通过职称认证,无需重复提交!");
				}else{
					Object obj = ydMangerServiceUser.$jobTitleVerified(userVo, level);
					request.getSession().setAttribute("user", userVo);
					return obj;
				}
			} else {
				mapInitFactory.setMsg("600", "请登录后再进行操作!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
}
