package cn.syl.blogmain.service;

import cn.syl.blogmain.pojo.Authority;
import org.springframework.data.domain.Page;

/**
 * @author: syl  Date: 2017/11/2 Email:nerosyl@live.com
 */
public interface AuthorityService {
    void saveAuthority(Authority category);
    void insertAuthorities(Iterable<Authority> iterable);
    void deleteAuthorityById(Long id);
    void updateAuthority(Authority category);
    Iterable<Authority> findAll();
    Authority findAuthorityById(Long id);
   // Authority findAuthorityByAuthorityname(String Authorityname);

    /**
     *
     * @param starPage 从那一页开始的
     * @param itemNumber 每页显示的个数
     * @return
     */
    Page<Authority> findAuthorityByPage(Integer starPage, Integer itemNumber );

    Page<Authority> findAuthorityByPageAndOrder(Integer starPage,Integer itemNumber );
}
