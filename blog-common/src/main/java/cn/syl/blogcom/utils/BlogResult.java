package cn.syl.blogcom.utils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 *
 * {"status":200,"msg":"OK",
 * "data":
 * {"id":99,"parentId":88,"name":"1111","status":1,"sortOrder":1,"isParent":false,"created":1502359154651,"updated":1502359154651}
 * }
 * @author: syl  Date: 2017/11/5 Email:nerosyl@live.com
 */
public class BlogResult implements Serializable {


   @Getter @Setter
    private Integer success; //1 成功 -1失败

    @Getter @Setter
    private String msg;

    @Getter @Setter
    private Object data;

 public BlogResult(Integer success, String msg, Object data) {
  this.success = success;
  this.msg = msg;
  this.data = data;
 }

 public BlogResult(Object data) {
  this.success = 1;
  this.msg = "OK";
  this.data = data;
 }

 public static BlogResult ok(Object data) {
  return new BlogResult(data);
 }

 public static BlogResult no(String msg) {
  return new BlogResult(-1,  msg,null);
 }


}
