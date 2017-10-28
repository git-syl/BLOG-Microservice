package cn.syl.blogmain.repository;

import cn.syl.blogmain.pojo.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: syl  Date: 2017/10/29 Email:nerosyl@live.com
 */
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
