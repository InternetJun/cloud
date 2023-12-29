package org.example.auth.controller;


import io.swagger.annotations.ApiOperation;
import org.example.auth.service.IClient;
import org.example.auth.vo.client.ClientVo;
import org.example.common.core.httpEntity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/15 14:22
 */
@RestController
@RequestMapping("/client")
@ApiOperation(value = "客户端处理")
public class ClientController {

    @Autowired
    private IClient clientService;

    @PostMapping("/addClient")
    public Result addClient(@RequestBody ClientVo clientVo) {
        return clientService.addClient(clientVo);
    }
}
