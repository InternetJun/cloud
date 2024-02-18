package com.example.stock.service;

import org.springframework.stereotype.Service;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/1/2 15:00
 */
@Service
public class TestService {
    public Integer getSum() {
        return 10/0;
    }
}
