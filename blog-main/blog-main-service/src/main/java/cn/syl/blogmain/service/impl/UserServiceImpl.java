package cn.syl.blogmain.service.impl;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.JsonUtils;
import cn.syl.blogcom.utils.ShiroMD5;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.UserRepository;
import cn.syl.blogmain.service.UserService;
import cn.syl.jedis.IJedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
@Service
public class UserServiceImpl implements UserService{

    @Value("${password.salt}")
    private String salt;

    //user.session=USER_SESSION:
    @Value("${user.session}")
    private String USER_SESSION_PRE;

    @Value("${session.expire}")
    private int sec;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private IJedis jedis;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void insertUsers(Iterable<User> iterable) {
        userRepository.save(iterable);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }


    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    //@Transactional//解决懒加载
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
       // return userRepository.findUserByUsername(username);
    }

    @Override
    public Page<User> findUserByPage(Integer starPage, Integer itemNumber) {
        PageRequest pageRequest = new PageRequest(starPage,itemNumber);
        return  userRepository.findAll(pageRequest);

    }

    @Override
    public Page<User> findUserByPageAndOrder(Integer starPage, Integer itemNumber) {
        List<Sort.Order> orders = new ArrayList<>();
        // orders.add(new Sort.Order(Sort.Direction.DESC,"name"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
        //  orders.add(new Sort.Order(Sort.Direction.ASC,"parentId"));
        PageRequest pageRequest = new PageRequest(starPage,itemNumber,new Sort(orders));
        return userRepository.findAll(pageRequest);
    }

    @Override
    public void delTokenByCookieToken(String cookieToken) {
        Long del = jedis.del(cookieToken);
        System.err.println("删除redis session token--->"+del);
    }

    @Override
    public User findUserByToken(String token){
        String userString = jedis.get(USER_SESSION_PRE + token);
        if (!StringUtils.isEmpty(userString)){
            return JsonUtils.jsonToPojo(userString, User.class);
        }
        return null;

    }






}
