package com.gala.bug.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gala_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;//职位编号

    @Column(name = "rolename")
    private String roleName;//职位名称

    @Column(name = "leve1")
    private Integer leve1;//职位等级

}
