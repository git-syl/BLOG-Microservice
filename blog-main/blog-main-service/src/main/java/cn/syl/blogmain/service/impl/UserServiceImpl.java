package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.UserRepository;
import cn.syl.blogmain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;

    // @Autowired
    private JedisCluster jedisCluster;

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
    @Transactional//解决懒加载
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
}
