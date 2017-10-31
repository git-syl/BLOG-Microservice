package cn.syl.blogmain.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: syl  Date: 2017/10/31 Email:nerosyl@live.com
 */
@Entity
//@Table(name = "t_authority")
@NoArgsConstructor
@ToString
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    //父权限
    @ManyToOne
    @JoinColumn(name = "pid")
    @Fetch(FetchMode.SELECT)
    @Getter
    @Setter
    private Authority parentFunction;

    //子权限
    @OneToMany(mappedBy = "parentFunction")
    //@JoinColumn(name = "pid")//Caused by: org.hibernate.AnnotationException: Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn: cn.syl.blogmain.pojo.Authority.children
    @Fetch(FetchMode.SUBSELECT)
    @Getter
    @Setter
    private Set<Authority> children = new HashSet<>(0);

//     @Column @Getter @Setter
//     private String pId;//父权限的id
    @Column(length = 64)
    @Getter
    @Setter
    private String name;
    @Column(length = 64)
    @Getter
    @Setter
    private String code;
    //权限对应的url
    @Column
    @Getter
    @Setter
    private String url;

    //是否生成菜单 1表示生成，0表示不生成
    @Column
    @Getter
    @Setter
    private Byte generatorMenu = 1;

    //@ManyToMany(mappedBy = "authorities")
    @ManyToMany
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
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
