package com.gala.bug.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gala_payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//职位编号

    @Column(name = "cashierid")
    private Integer cashierid;//财务id

    @Column(name = "staffid")
    private Integer staffid;//员工编号

    @Column(name = "basepay")
    private Double basepay;//基本工资

    @Column(name = "insurance")
    private Double insurance;//五险一金

    @Column(name = "bonus")
    private Double bonus;//绩效奖金

    @Column(name = "subsidy")
    private Double subsidy;//补贴

}
