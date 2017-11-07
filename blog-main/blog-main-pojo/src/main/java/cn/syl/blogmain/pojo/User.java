package cn.syl.blogmain.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author: syl  Date: 2017/10/31 Email:nerosyl@live.com
 */
@Entity
@NoArgsConstructor
//@ToString
@JsonFilter("exRoleF")
public class User implements Serializable {

//    public User() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 16)
    @Getter
    @Setter
    private String username;

    @Column(length = 16)
    @Getter
    @Setter
    private String name;

    @Column(length = 64)
    @Getter
    @Setter
    private String password;
    @Column
    @Getter
    @Setter
    private Date birthday;
    @Column(length = 4)
    @Getter
    @Setter
    private String gender;
    @Column(length = 64)
    @Getter
    @Setter
    private String telephone;
    @Column(length = 128)
    @Getter
    @Setter
    private String remark;

   // @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    private Set<Role> roles = new HashSet<>(0);

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

}
