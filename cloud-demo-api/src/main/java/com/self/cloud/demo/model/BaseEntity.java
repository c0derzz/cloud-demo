package com.self.cloud.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 17:20
 * @Description:
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11)
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at",nullable = false,columnDefinition = "datetime")
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at",nullable = false,columnDefinition = "datetime")
    private Date updateAt;

    @Column(name = "create_by",length = 11,columnDefinition = "int default 0")
    private Integer createBy;

    @Column(name = "update_by",length = 11,columnDefinition = "int default 0")
    private Integer updateBy;

}
