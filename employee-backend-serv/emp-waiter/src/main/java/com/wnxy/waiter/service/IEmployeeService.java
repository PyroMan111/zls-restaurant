package com.wnxy.waiter.service;

import com.wnxy.waiter.model.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-27
 */
public interface IEmployeeService extends IService<Employee> {

    //    根据ordererId查出手机号
    String queryTel(Long ordererId);
}
