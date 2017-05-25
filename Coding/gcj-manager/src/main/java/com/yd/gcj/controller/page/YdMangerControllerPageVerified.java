package com.yd.gcj.controller.page;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yd.gcj.entity.YdMangerPhoneCode;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceVerified;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.util.MyStaticFactory;

@RestController
@RequestMapping(value = "/page/verified")
public class YdMangerControllerPageVerified {

	@Autowired
	private YdMangerServiceVerified serviceVerified;

	@RequestMapping("/insert")
	public Object insert(Integer vId, Integer userId, String name, String phone, String phoneCode, String idNum,
			Integer yosId, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
			MapInitFactory mapInitFactory = new MapInitFactory();
			YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute("smrz");
			if (pc != null) {
				if (userVo != null && userVo.getUser_phone().equals(phone)) {
					if (pc.getPhone().equals(phone)) {
						if (pc.getCode().equals(phoneCode)) {
							if (new Date().getTime() - pc.getTime() < 600000) {
								return serviceVerified.submitUserMsg(vId, userId, name, idNum, yosId);
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
					mapInitFactory.setMsg("600", "对不起，您没有登录，请登录后再试！").getMap();
				}
			} else {
				mapInitFactory.setMsg("501", "发送验证码后再进行操作！");
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	@RequestMapping(value = "/caraupload/{userId}", method = RequestMethod.POST)
	public Object carAUpLoad(@PathVariable Integer userId, MultipartFile m, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				try {
					if (m != null) {
						String path = MyStaticFactory.systemPath + MyStaticFactory.verifiedImg;
						String imgType = getFileType(m.getOriginalFilename());
						if (isImg(imgType)) {
							if (m.getSize() < (1024 * 1024 * 10)) {
								String fileName = userId + "_" + System.currentTimeMillis() + imgType;
								String imgPath = path + fileName;
								String dbpath = MyStaticFactory.verifiedImg + fileName;
								File file = new File(path);
								if (!file.exists()) {
									file.mkdirs();
								}
								file = new File(imgPath);
								m.transferTo(file);
								return serviceVerified.carAUpLoad(userId, dbpath);
							} else {
								mapInitFactory.setMsg("503", "文件大小超出限制，请上传小于10MB的图片！");
							}
						} else {
							mapInitFactory.setMsg("502", "图片格式不正确！");
						}
					} else {
						mapInitFactory.setMsg("501", "请选择图片！");
					}
				} catch (Exception e) {
					e.printStackTrace();
					mapInitFactory.setSystemError();
				}
			} else {
				mapInitFactory.setMsg("600", "对不起，您没有操作权限！");
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return mapInitFactory.setSystemError().getMap();
		}

	}

	@RequestMapping(value = "/carbupload/{userId}", method = RequestMethod.POST)
	public Object carBUpLoad(@PathVariable Integer userId, MultipartFile m, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				try {
					if (m != null) {
						String path = MyStaticFactory.systemPath + MyStaticFactory.verifiedImg;
						String imgType = getFileType(m.getOriginalFilename());
						if (isImg(imgType)) {
							if (m.getSize() < (1024 * 1024 * 10)) {
								String fileName = userId + "_" + System.currentTimeMillis() + imgType;
								String imgPath = path + fileName;
								String dbpath = MyStaticFactory.verifiedImg + fileName;
								File file = new File(path);
								if (!file.exists()) {
									file.mkdirs();
								}
								file = new File(imgPath);
								m.transferTo(file);
								return serviceVerified.carBUpLoad(userId, dbpath);
							} else {
								mapInitFactory.setMsg("503", "文件大小超出限制，请上传小于10MB的图片！");
							}
						} else {
							mapInitFactory.setMsg("502", "上传文件格式不正确！");
						}
					} else {
						mapInitFactory.setMsg("501", "请选择图片！");
					}
				} catch (Exception e) {
					e.printStackTrace();
					mapInitFactory.setSystemError();
				}
			} else {
				mapInitFactory.setMsg("600", "对不起，您没有操作权限！");
			}
			return mapInitFactory.getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return mapInitFactory.setSystemError().getMap();
		}

	}

	private boolean isImg(String type) {
		boolean isOk = false;
		String[] types = { ".png", ".jpg", ".jpeg" };
		for (String t : types) {
			if (t.toLowerCase().equals(type)) {
				isOk = true;
			}
		}
		return isOk;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFileType(String fileName) {
		if (fileName != null && fileName.indexOf(".") >= 0) {
			return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		return "";
	}
}
