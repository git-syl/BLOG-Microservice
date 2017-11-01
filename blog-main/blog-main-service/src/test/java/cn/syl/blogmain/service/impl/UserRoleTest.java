package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Role;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.RoleRepository;
import cn.syl.blogmain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: syl  Date: 2017/10/31 Email:nerosyl@live.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setName("张三");
        user.setUsername("54544@qq.com");
        user.setPassword("13454654");
        user.setBirthday(new Date());
        user.setGender("男");
        user.setTelephone("165454");
        userRepository.save(user);
    }

    @Test
    public void testAddRole(){
        Role role = new Role();
        role.setName("管理员");
        role.setCode("admin");
        roleRepository.save(role);
    }

    @Test
    public void testAddRoleToUser(){
        Role role = new Role();
        role.setName("超级管理员");
        role.setCode("su");

        User user = new User();
        user.setName("李四");
        user.setUsername("11114@qq.com");
        user.setPassword("1345764654");
        user.setBirthday(new Date());
        user.setGender("男");
        user.setTelephone("165454");

        //关联
        role.getUsers().add(user);

        roleRepository.save(role);
    }


}
