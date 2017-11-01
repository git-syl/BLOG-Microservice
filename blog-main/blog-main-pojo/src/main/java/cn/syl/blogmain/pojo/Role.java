package cn.syl.blogmain.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author: syl  Date: 2017/10/31 Email:nerosyl@live.com
 */
@Entity
@NoArgsConstructor
//@ToString
public class Role implements Serializable {
//    public Role() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 64)
    @Getter
    @Setter
    private String name;
    @Column(length = 64)
    @Getter
    @Setter
    private String code;//权限关键字
    @Column
    @Getter
    @Setter
    private String description;

    //todo:
    //@ManyToMany(mappedBy = "roles",cascade = CascadeType.PERSIST)
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
   // @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
  //  @Getter
   // @Setter
    private Set<User> users = new HashSet<>(0);

  //  @ManyToMany(cascade = CascadeType.ALL)//对角色操作 级联角色的权限
    @ManyToMany(fetch = FetchType.EAGER)//对角色操作 级联角色的权限
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    @Getter
    @Setter
    private Set<Authority> authorities = new HashSet<>(0);

    //common:
    /**
     * -1已经删除 0禁用 1启用
     */
    @Column
    @Getter
    @Setter
    private Byte status = 1;

    @Column
    @Getter
    @Setter
    private Integer sortOrder = 1;

    @Column(length = 64)
    @Getter
    @Setter
    private String createBy;

    @Column
    @Getter
    @Setter
    private Date createTime;

    @Column
    @Getter
    @Setter
    private Date updateTime;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
