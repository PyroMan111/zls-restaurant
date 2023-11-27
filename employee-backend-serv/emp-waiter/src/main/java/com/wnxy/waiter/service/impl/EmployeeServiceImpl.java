package com.wnxy.waiter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.waiter.mapper.EmployeeMapper;
import com.wnxy.waiter.model.entity.Employee;
import com.wnxy.waiter.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    //    根据ordererId查出手机号
    @Override
    public String queryTel(Long ordererId) {
        Employee employee = this.getById(ordererId);
        if (employee != null && Optional.ofNullable(employee.getPhone()).isPresent()) {
            return employee.getPhone();
        } else {
            return null;
        }
    }


    /**this.getById(ordererId).getPhone()*/
}
