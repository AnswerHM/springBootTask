package com.humin.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @Author:humin
 * @Date:27/07/20182:57 PM
 */
@Service
public class AsyncService {

    // 告诉Spring这是一个异步方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中。。。");
    }
}
