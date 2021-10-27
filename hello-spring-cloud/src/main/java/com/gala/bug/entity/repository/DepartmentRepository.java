package com.gala.bug.entity.repository;

import com.gala.bug.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<操作对象，主键类型>
 */
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}