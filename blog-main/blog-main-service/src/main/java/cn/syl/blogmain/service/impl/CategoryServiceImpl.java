package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Category;
import cn.syl.blogmain.repository.CategoryRepository;
import cn.syl.blogmain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public void insertCategories(Iterable<Category> iterable) {
        categoryRepository.save(iterable);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }


    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Page<Category> findCategoryByPage(Integer starPage, Integer itemNumber) {
        PageRequest pageRequest = new PageRequest(starPage,itemNumber);
        return  categoryRepository.findAll(pageRequest);

    }

    @Override
    public Page<Category> findCategoryByPageAndOrder(Integer starPage, Integer itemNumber) {
        List<Sort.Order> orders = new ArrayList<>();
       // orders.add(new Sort.Order(Sort.Direction.DESC,"name"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
      //  orders.add(new Sort.Order(Sort.Direction.ASC,"parentId"));
        PageRequest pageRequest = new PageRequest(starPage,itemNumber,new Sort(orders));
        return categoryRepository.findAll(pageRequest);
    }


}
