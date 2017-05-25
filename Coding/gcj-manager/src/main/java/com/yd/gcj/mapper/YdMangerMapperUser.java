package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.vo.YdMangerUserDetail;
import com.yd.gcj.entity.vo.YdMangerUserVo;

public interface YdMangerMapperUser {
	
	/***
	 * 查看用户基本信息，主要用于雇主查看人才大厅时使用
	 * @param userId 用户id
	 * @param startPageNum 	（开始条数）
	 * @param queryPageNum	（查询条数）
	 * @return
	 */
	List<YdMangerUserVo> $queryAllInLogin(@Param("userId") Integer userId,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查看用户基本信息，主要用于雇主查看人才大厅时使用
	 * @param startPageNum 	（开始条数）
	 * @param queryPageNum	（查询条数）
	 * @return
	 */
	List<YdMangerUserVo> $queryAll(@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	
	/**
	 * 查询用户数据数量
	 * @return
	 */
	Integer $queryCountByS();
	
	/**
	 * 查询指定条数服务商完成任务排前用户信息
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	Integer $queryCountByRSP();
	
	/**
	 * 查询指定完成任务最多数量服务商用户信息
	 * @return
	 */
	List<YdMangerUser> $queryRSP(@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 根据用户id查询用户信息
	 * @param user_id
	 * @return 
	 */
	YdMangerUserVo $queryById(@Param("userId") Integer userId);
	
	/**
	 * 查询用户详情
	 * @param userId
	 * @return
	 */
	YdMangerUserDetail $queryDetailById(@Param("userId") Integer userId);
	
	/***
	 * 查询指定用户头像地址
	 * @Description: 
	 * @return    
	 * @date: 2017年2月15日 下午4:26:14
	 */
	String $queryUserImgPath(@Param("userId") Integer userId);
	
	/***
	 * 根据用户id与手机号查询用户支付密码
	 * @Description: 
	 * @param payPwd
	 * @param phone
	 * @return    
	 * @date: 2017年3月8日 下午5:26:18
	 */
	String $queryPayPwdByUserPhoneAndUserId(@Param("userId") Integer userId,@Param("phone") String phone);
	
	/***
	 * 获取用户余额
	 * @Description: 
	 * @param userId
	 * @return    
	 * @date: 2017年3月8日 下午5:32:00
	 */
	float $queryPriceByUserId(@Param("userId") Integer userId);
	
	/***
	 * 检查用户是否存在 
	 * @param user_phone
	 * @return 0不存在 1存在
	 */
	Integer $userIsExist(@Param("user_phone") String user_phone);
	
	/***
	 * 根据用户id检查是否该用户存在
	 * @param user_id
	 * @return
	 */
	Integer $userIsExistById(@Param("user_id") Integer user_id);
	
	/***
	 * 查询用户是否实名认证
	 * @return
	 */
	Integer $queryVerifiedByUserId(@Param("userId") Integer userId);
	
	/***
	 * 注册用户信息
	 * @param user_phone    	用户注册手机号
	 * @param user_pwd	密码
	 * @return 
	 */
	Integer $regist(YdMangerUser user);
	
	/***
	 * 根据手机号查询用户信息
	 * @param user_phone
	 * @return 
	 */
	YdMangerUserVo $queryByPhone(@Param("user_phone") String user_phone);
	
	/**
	 * 根据手机号与用户类型查询账户信息（主要为登陆使用）
	 * @param user_phone
	 * @param user_type
	 * @return
	 */
	YdMangerUserVo $queryByPhoneAndType(@Param("user_phone") String user_phone,@Param("user_type") String user_type);
	
	/***
	 * 更新用户信息，主要用于添加用户基本信息和修改用户基本信息
	 * @param ydMangerUser
	 * @return
	 */
	Integer $update(YdMangerUser ydMangerUser);
	
	/**
	 * 修改用户昵称
	 * @param userId
	 * @param nickname
	 * @return
	 */
	Integer $updateNickname(@Param("userId") Integer userId,@Param("nickname") String nickname);
	
	/**
	 * 修改用户姓名
	 * @param userId
	 * @param userName
	 * @return
	 */
	Integer $updateUserName(@Param("userId") Integer userId,@Param("userName") String userName);
	
	/**
	 * 修改用户所在地
	 * @param userId
	 * @param userAddr
	 * @return
	 */
	Integer $updateAddr(@Param("userId") Integer userId,@Param("userAddr") String userAddr);
	
	/**
	 * 修改用户简历信息
	 * @param userId
	 * @param userResume
	 * @return
	 */
	Integer $updateResume(@Param("userId") Integer userId,@Param("userResume") String userResume);
	
	/**
	 * 修改用户邮箱
	 * @param userId
	 * @param userEmail
	 * @return
	 */
	Integer $updateEmail(@Param("userId") Integer userId,@Param("userEmail") String userEmail);
	
	/**
	 * 修改用户QQ号码
	 * @param userId
	 * @param userQQ
	 * @return
	 */
	Integer $updateQQ(@Param("userId") Integer userId,@Param("userQQ") String userQQ);
	
	/***
	 * 重置登录密码
	 * @param user_id  用户id
	 * @param user_pwd 新密码
	 * @return
	 */
	Integer $updatePwd(@Param("user_id") Integer user_id,@Param("user_pwd") String user_pwd);
	
	/***
	 * 重置交易密码
	 * @param user_id  用户id
	 * @param user_ppwd 新密码
	 * @return
	 */
	Integer $updatePPwd(@Param("user_id") Integer user_id,@Param("user_ppwd") String user_ppwd);
	
	/***
	 * 查询用户数据总记录
	 * @return
	 */
	Integer $queryCountNumBySql(@Param("sql") String sql);
	
	/***
	 * 根据手机号和登录密码检查账号是否存在
	 * @param user_phone
	 * @param user_pwd
	 * @return
	 */
	Integer $queryIdByPhoneAndPwd(@Param("userId") Integer userId,@Param("loginPwd") String loginPwd);
	
	/***
	 * 根据用户Id手机号检查账号是否存在
	 * @param user_phone
	 * @return
	 */
	Integer $queryByIdAndPhone(@Param("userId") Integer userId,@Param("phone") String phone);
	
	/***
	 * 提交企业认证审核数据
	 * @param user_cstate
	 * @param user_ename
	 * @return
	 */
	Integer $enterCFA(@Param("user_id") Integer user_id,@Param("user_cstate") Integer user_cstate,@Param("user_ename") String user_ename);
	
	/***
	 * 修改用户专业(技能)认证状态
	 * @param userId
	 * @param state
	 * @return
	 */
	Integer $updateSkillstate(@Param("userId") Integer userId,@Param("state") Integer state);
	
	/***
	 * 更新用户接单类型信息
	 * @param userId
	 * @param otype
	 * @return
	 */
	Integer $updateOtype(@Param("userId") Integer userId,@Param("otype") Integer otype);
	
	/***
	 * 修改用户手机号
	 * @param userId
	 * @param phone
	 * @return
	 */
	Integer $updateUserPhone(@Param("userId") Integer userId,@Param("phone") String phone);


	/***
	 * 修改用户密码
	 * @param userPhone
	 * @param userPwd
	 * @return
	 */
	Integer $forgetPwd(@Param("userPhone") String userPhone,@Param("userPwd") String userPwd);
	
	/**
	 * 保持用户头像
	 * @Description: 
	 * @param userId
	 * @param imgPath
	 * @return    
	 * @date: 2017年2月15日 下午4:23:01
	 */
	Integer $updateUserAvatar(@Param("userId") Integer userId,@Param("avatar") String avatar);
	
	/***
	 * 修改实名认证状态
	 * @param userId
	 * @param verified
	 * @return
	 */
	Integer $updateVerifiedState(@Param("userId") Integer userId,@Param("verified") Integer verified);
	
	/***
	 * 添加用户职称及认证状态
	 * @param userId
	 * @param jobTitle
	 * @param level
	 * @return
	 */
	Integer $updateJobTitleAndLevel(@Param("userId") Integer userId,@Param("jobTitle") Integer jobTitle,@Param("level") Integer level);
	
	/***
	 * 修改用户职称认证状态
	 * @param userId
	 * @param jobTitle
	 * @return
	 */
	Integer $updateJobTitle(@Param("userId") Integer userId,@Param("jobTitle") Integer jobTitle);
	
	/**
	 * 修改用户账户金额
	 * @Description: 
	 * @param userId
	 * @param price
	 * @return    
	 * @date: 2017年3月8日 下午5:38:38
	 */
	Integer $updateUserPrice(@Param("userId") Integer userId,@Param("price") float price);
	
}