package com.gala.bug.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gala_staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffid;//员工编号

    @Column(name = "departmentid")
    private Integer departmentid;//部门编号

    @Column(name = "roleid")
    private Integer roleid;//职位编号

    @Column(name = "staffname")
    private String staffname;//姓名

    @Column(name = "password")
    private String password;//密码

    @Column(name = "certid")
    private String certid;//身份证号

    @Column(name = "mobileno")
    private String mobileno;//手机号码

    @Column(name = "sex")
    private String sex;//性别

    @Column(name = "address")
    private String address;//通讯地址

    @Column(name = "email")
    private String email;//邮箱

}
