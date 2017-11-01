package cn.syl.blogmain.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: syl  Date: 2017/10/28 Email:nerosyl@live.com
 */
///java.lang.IllegalStateException: Serialized class cn.syl.blogmain.pojo.Category must implement java.io.Serializable
@Entity
@ToString
@NoArgsConstructor
public class Category implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(length = 50) @Getter @Setter
    private String name;

    //0为根节点
    @Column(length = 20) @Getter @Setter
    private String parentId;

    /**
     *  true有父级别菜单 false根菜单
     */
    @Column @Getter @Setter
    private Boolean hasParent;

    //common:
    /**
     *  -1已经删除 0禁用 1启用
     */
    @Column @Getter @Setter
    private Byte status=1;

    @Column @Getter @Setter
    private Integer sortOrder=1;

    @Column(length = 64) @Getter @Setter
    private String createBy;

    @Column @Getter @Setter
    private Date createTime;

    @Column @Getter @Setter
    private Date updateTime;


}
