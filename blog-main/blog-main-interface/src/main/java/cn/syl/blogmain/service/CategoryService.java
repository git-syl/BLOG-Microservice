package cn.syl.blogmain.service;

import cn.syl.blogmain.pojo.Category;
import org.springframework.data.domain.Page;

/**
 * @author: syl  Date: 2017/10/29 Email:nerosyl@live.com
 */
public interface CategoryService {
    void saveCategory(Category category);
    void insertCategories(Iterable<Category> iterable);
    void deleteCategoryById(Long id);
    void updateCategory(Category category);
    Iterable<Category> findAll();
    Category findCategoryById(Long id);

    /**
     *
     * @param starPage 从那一页开始的
     * @param itemNumber 每页显示的个数
     * @return
     */
    Page<Category> findCategoryByPage(Integer starPage,Integer itemNumber );

    Page<Category> findCategoryByPageAndOrder(Integer starPage,Integer itemNumber );



}
