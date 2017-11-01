package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Authority;
import cn.syl.blogmain.pojo.Role;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.AuthorityRepository;
import cn.syl.blogmain.repository.RoleRepository;
import cn.syl.blogmain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertDataTest {

    @Resource
    AuthorityRepository authorityRepository;
    @Resource
    RoleRepository roleRepository;
    @Resource
    UserRepository userRepository;

    @Test
    public void insertIntoFunction(){
        Authority authority = new Authority();
        authority.setName("用户管理");
        authority.setCode("userInfo:view");
        authority.setResourceType("menu");
        authority.setUrl("userInfo/userList");

        Authority authority1 = new Authority();
        authority1.setName("用户添加");
        authority1.setCode("userInfo:add");
        authority1.setResourceType("button");
        authority1.setUrl("userInfo/userList");
        authority1.setParentFunction(authority);

        Authority authority2 = new Authority();
        authority2.setName("用户删除");
        authority2.setCode("userInfo:del");
        authority2.setResourceType("button");
        authority2.setUrl("userInfo/userList");
        authority2.setParentFunction(authority);

        authorityRepository.save(authority);
        authorityRepository.save(authority1);
        authorityRepository.save(authority2);

        Role role = new Role();
        role.setName("管理员");
        role.setCode("admin");

        Role role1 = new Role();
        role1.setName("会员");
        role1.setCode("vip");

        role.getAuthorities().add(authority);
        role.getAuthorities().add(authority1);
        role.getAuthorities().add(authority2);

        role1.getAuthorities().add(authority2); //保存role级联 权限

        roleRepository.save(role);      //级联保存引起的重复插入主键
        roleRepository.save(role1);    //Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: cn.syl.blogmain.pojo.Authority

        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        user.getRoles().add(role);

        userRepository.save(user);


    }
}
