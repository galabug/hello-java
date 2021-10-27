package com.gala.bug.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gala_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentid;//部门编号

    @Column(name = "departmentname")
    private String departmentname;//部门编号

    @Column(name = "parentid")
    private Integer parentid;//上级部门id

    @Column(name = "level")
    private Integer level;//部门等级
}
