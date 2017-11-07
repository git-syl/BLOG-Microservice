package cn.syl.blogcom.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author: syl  Date: 2017/11/5 Email:nerosyl@live.com
 */
public class ShiroMD5 {
    public static String md5(String password, String salt){
        return new Md5Hash(password,salt,2).toString();
    }
}
