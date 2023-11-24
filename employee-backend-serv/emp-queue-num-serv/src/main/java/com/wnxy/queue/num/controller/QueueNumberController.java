package com.wnxy.queue.num.controller;


import com.wnxy.common.entity.QueueNumber;
import com.wnxy.queue.num.service.IFloorService;
import com.wnxy.queue.num.service.IQueueNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/queue-number")
public class QueueNumberController {
    @Autowired
    private IQueueNumberService queueNumberService;

    @PostMapping("/add")
    public boolean add(@RequestBody QueueNumber queueNumber){
        boolean res = queueNumberService.save(queueNumber);
        return res;
    }


}

