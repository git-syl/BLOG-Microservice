package cn.syl.blogmain.service;

import cn.syl.blogmain.pojo.User;
import org.springframework.data.domain.Page;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
public interface UserService {

    void saveUser(User category);
    void insertUsers(Iterable<User> iterable);
    void deleteUserById(Long id);
    void updateUser(User category);
    Iterable<User> findAll();
    User findUserById(Long id);
    User findUserByUsername(String username);

    /**
     *
     * @param starPage 从那一页开始的
     * @param itemNumber 每页显示的个数
     * @return
     */
    Page<User> findUserByPage(Integer starPage, Integer itemNumber );

    Page<User> findUserByPageAndOrder(Integer starPage,Integer itemNumber );

}
