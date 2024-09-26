package com.example.stock.controller;

import com.example.stock.annotations.Compose;
import com.example.stock.annotations.Test;
import com.example.stock.service.TestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.example.common.core.httpEntity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lejun
 */
@RestController
@ApiOperation("测试mvc类")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/getOne")
    @ApiOperation(value = "获取测试方法")
    @Test(name = "test1")
    public Result get() {
        return Result.success(testService.getSum());
    }

    @PostMapping("/getTwo")
    @Test(name = "test2")
    public String getTwo(@RequestParam boolean isExport) {
        return "test2";
    }

    /**
     * 需要完成的是有compose的注解的时候，对Test注解的进一步处理
     * */
    @PostMapping("/getComponse")
    @Compose(value = "结合")
    public String getCompose(@ApiParam(value = "{}") @RequestBody Map<String, Object> map) {
        return "compose";
    }
}
