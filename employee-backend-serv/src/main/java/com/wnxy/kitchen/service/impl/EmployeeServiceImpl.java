package com.wnxy.kitchen.service.impl;

import com.wnxy.common.entity.Employee;
import com.wnxy.kitchen.mapper.EmployeeMapper;
import com.wnxy.kitchen.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
