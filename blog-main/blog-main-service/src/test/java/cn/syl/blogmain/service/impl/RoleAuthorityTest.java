package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Authority;
import cn.syl.blogmain.pojo.Role;
import cn.syl.blogmain.repository.AuthorityRepository;
import cn.syl.blogmain.repository.RoleRepository;
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
public class RoleAuthorityTest {
    @Resource
    private AuthorityRepository authorityRepository;
    @Resource
    private RoleRepository roleRepository;
    @Test
    public void testAddFunction(){
        Authority authority = new Authority();
        authority.setName("编辑");
        authority.setCode("edit");
        authority.setGeneratorMenu(true);
        authorityRepository.save(authority);
    }

    @Test
    public void testAddFunctionAndParentFunction1(){
        Authority authority = new Authority();
        authority.setName("编辑");
        authority.setCode("edit");
        authority.setGeneratorMenu(true);

        Authority authorityP = new Authority();
        authorityP.setName("查看");
        authorityP.setCode("look");
        authorityP.setGeneratorMenu(true);


        authorityP.getChildren().add(authority);

        authorityRepository.save(authority);
        authorityRepository.save(authorityP);

    }

    @Test
    public void testAddFunctionAndParentFunction2(){
        Authority authority = new Authority();
        authority.setName("编辑");
        authority.setCode("edit");
        authority.setGeneratorMenu(true);

        Authority authorityP = new Authority();
        authorityP.setName("查看");
        authorityP.setCode("look");
        authorityP.setGeneratorMenu(true);

        authority.setParentFunction(authorityP);

        authorityRepository.save(authorityP);   //注意 顺序
        authorityRepository.save(authority);

        //authority.setParentFunction(authorityP);  不是说持久化 对象 有自动更新的吗 ??可能jpa不其效果 ??todo:等待查阅文档

       // org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException:
        // object references an unsaved transient instance - save the transient instance before flushing :
        // cn.syl.blogmain.pojo.Authority.parentFunction -> cn.syl.blogmain.pojo.Authority; nested exception is java.lang.IllegalStateException:
        // org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : cn.syl.blogmain.pojo.Authority.parentFunction -> cn.syl.blogmain.pojo.Authority

        //  Caused by: java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException:
        // object references an unsaved transient instance - save the transient instance before flushing :
        // cn.syl.blogmain.pojo.Authority.parentFunction -> cn.syl.blogmain.pojo.Authority

    }

    //测试级联
    @Test
    public void testAddRoleAndFuncton(){
        Role role = new Role();
        role.setName("管理员");
        role.setCode("admin");


        Authority authority = new Authority();
        authority.setName("编辑");
        authority.setCode("edit");
        authority.setGeneratorMenu(true);

      //  authority.getRoles().add(role);
        role.getAuthorities().add(authority);


        roleRepository.save(role);
      //  authorityRepository.save(authority);




    }


}
