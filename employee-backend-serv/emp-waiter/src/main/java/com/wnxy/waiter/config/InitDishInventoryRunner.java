package com.wnxy.waiter.config;

import com.wnxy.waiter.model.entity.DishInventoryDto;
import com.wnxy.waiter.redisConstant.RedisConstant;
import com.wnxy.waiter.service.IDishDetailService;
//import com.woniuxy.portal.entity.Book;
//import com.woniuxy.portal.redisConstant.RedisConstant;
//import com.woniuxy.portal.repository.BookRepository;
//import com.woniuxy.portal.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在有加入Redis缓存后：
 * 依然存在的问题：项目一上线，瞬间有10W个请求同时访问首页，redis中没有，就查询了数据库，直
 * 接造成系统崩溃
 * <p>
 * 缓存击穿：redis没有，数据库有。 通过数据预热解决
 * 解决方案：数据预热
 */

/**
 * CommandLineRunner
 * 1、Spring Boot提供了CommandLineRunner接口，您可以使用他完成项目启动时候执行一些初始化操
 * 作。
 * 2、加载配置、初始化数据库连接、预加载数据（数据预热）
 */
@Component
public class InitDishInventoryRunner implements CommandLineRunner {
    //
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IDishDetailService dishDetailService;
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;

//    @Autowired
//    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        //        查询所有菜品，并获取库存
        List<DishInventoryDto> inventoryDtos = dishDetailService.queryAllSalesAndInventory();


//        elasticsearchRestTemplate.save(bookList);
//        bookRepository.saveAll(bookList);


//        遍历图书，加入到Redis的zset集合，buyCount购买量作为分值
        inventoryDtos.forEach(inventoryDto -> {
            redisTemplate.opsForZSet().add(
                    RedisConstant.DISH_LIST, inventoryDto.getId()+":"+inventoryDto.getName(), inventoryDto.getMonthlySales());


            redisTemplate.opsForHash().put("inventory", inventoryDto.getId().toString(), inventoryDto.getInventory());
        });
    }


}
