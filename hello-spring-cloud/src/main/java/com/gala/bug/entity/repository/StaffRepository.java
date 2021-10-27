package com.gala.bug.entity.repository;

import com.gala.bug.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * JpaRepository<操作对象，主键类型>
 */
public interface StaffRepository extends JpaRepository<Staff,Integer> {

}