package com.gala.bug.service.impl;

import com.alibaba.fastjson.JSON;
import com.gala.bug.entity.Department;
import com.gala.bug.entity.Staff;
import com.gala.bug.entity.repository.DepartmentRepository;
import com.gala.bug.entity.repository.PayrollRepository;
import com.gala.bug.entity.repository.RoleRepository;
import com.gala.bug.entity.repository.StaffRepository;
import com.gala.bug.service.GalaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@Service
public class GalaServiceImpl implements GalaService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StaffRepository staffRepository;

    private final Logger logger =  LoggerFactory.getLogger(GalaServiceImpl.class);

    public Map<String,Object> queryStaff(Map<String,Object> map) {
        List<Staff> departments = staffRepository.findAll();
        HashMap<String,Object> returnMap = new HashMap<>();
        returnMap.put("result",departments);

        logger.info(JSON.toJSONString(departments));
        return returnMap;
    }

    public Map<String,Object> queryDepartments(Map<String,Object> map) {
        List<Department> departments = departmentRepository.findAll();
        HashMap<String,Object> returnMap = new HashMap<>();
        returnMap.put("result",departments);
        return returnMap;
    }

}
