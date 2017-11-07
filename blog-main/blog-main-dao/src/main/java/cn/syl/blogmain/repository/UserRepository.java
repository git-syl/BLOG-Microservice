package cn.syl.blogmain.repository;

import cn.syl.blogmain.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: syl  Date: 2017/10/31 Email:nerosyl@live.com
 */
@Repository("userrepository")
public interface UserRepository  extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

}
