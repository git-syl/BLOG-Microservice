package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Category;
import cn.syl.blogmain.repository.CategoryRepository;
import cn.syl.blogmain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @author: syl  Date: 2017/10/29 Email:nerosyl@live.com
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void addJdisCache() {
        jedisCluster.set("t:username","uuuu1");
    }

    @Override
    public String getJdisCache() {
        return jedisCluster.get("t:username");
    }
}
