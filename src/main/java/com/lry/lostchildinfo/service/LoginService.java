package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.common.Result;

/**
 * @author : jdl
 * @description : 登录
 * @ClassName : LoginService
 * @create : 2022-01-23 13:38
 */
public interface LoginService {
    String login(String userCode,String userPwd);
}
