package cn.syl.sso.service;

import cn.syl.blogcom.utils.BlogResult;

/**
 * @author: syl  Date: 2017/11/6 Email:nerosyl@live.com
 */
public interface LoginService {
    BlogResult login(String username, String password);
}
