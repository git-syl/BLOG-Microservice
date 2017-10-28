package cn.syl.blogmain.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: syl  Date: 2017/10/28 Email:nerosyl@live.com
 */
///java.lang.IllegalStateException: Serialized class cn.syl.blogmain.pojo.Category must implement java.io.Serializable
@Entity
public class Category implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(length = 50) @Getter @Setter
    private String name;

    @Column(length = 20) @Getter @Setter
    private String parentId;

    @Column @Getter @Setter
    private Boolean hasParent;

    @Column @Getter @Setter
    private Byte status;

}
