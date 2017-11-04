import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author: syl  Date: 2017/11/2 Email:nerosyl@live.com
 */
public class MD5TEST {

    public static String md5(String password, String salt){
        return new Md5Hash(password,salt,2).toString();
    }

    public static void main(String[] args) {
      //  System.out.println(new Md5Hash("1234","admin",2).toString());
        //盐等于 username +盐
        System.out.println(new Md5Hash("1234","admin~!^%#!)(7897ow%$",2).toString());
    }
}
