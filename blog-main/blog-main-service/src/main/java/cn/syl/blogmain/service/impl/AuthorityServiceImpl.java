package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Authority;
import cn.syl.blogmain.repository.AuthorityRepository;
import cn.syl.blogmain.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: syl  Date: 2017/11/2 Email:nerosyl@live.com
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Resource
    private AuthorityRepository authorityRepository;

    // @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void saveAuthority(Authority user) {
        authorityRepository.save(user);
    }

    @Override
    public void insertAuthorities(Iterable<Authority> iterable) {
        authorityRepository.save(iterable);
    }

    @Override
    public void deleteAuthorityById(Long id) {
        authorityRepository.delete(id);
    }

    @Override
    public void updateAuthority(Authority user) {
        authorityRepository.save(user);
    }


    @Override
    public Iterable<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findAuthorityById(Long id) {
        return authorityRepository.findOne(id);
    }

//    @Override
//    //@Transactional//解决懒加载
//    public Authority findAuthorityByAuthorityname(String username) {
//        Authority user = authorityRepository.findAuthorityByAuthorityname(username);
//        return user;
//        // return authorityRepository.findAuthorityByAuthorityname(username);
//    }

    @Override
    public Page<Authority> findAuthorityByPage(Integer starPage, Integer itemNumber) {
        PageRequest pageRequest = new PageRequest(starPage,itemNumber);
        return  authorityRepository.findAll(pageRequest);

    }

    @Override
    public Page<Authority> findAuthorityByPageAndOrder(Integer starPage, Integer itemNumber) {
        List<Sort.Order> orders = new ArrayList<>();
        // orders.add(new Sort.Order(Sort.Direction.DESC,"name"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
        //  orders.add(new Sort.Order(Sort.Direction.ASC,"parentId"));
        PageRequest pageRequest = new PageRequest(starPage,itemNumber,new Sort(orders));
        return authorityRepository.findAll(pageRequest);
    }
}
