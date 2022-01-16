package com.macoloco.sheepy.controller;

import com.macoloco.sheepy.account.entity.Account;
import com.macoloco.sheepy.account.service.AccountService;
import com.macoloco.sheepy.common.vo.HttpResponse;
import com.macoloco.sheepy.common.vo.HttpStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Aya
 * @date 2022/1/16
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1/accounts")
@Api(tags = "账户管理")
public class AccountController {

    @Resource
    private AccountService accountService;

    @ApiOperation("注册用户")
    @PostMapping("/account")
    public HttpResponse<?> registerUser(@RequestBody Account account) {
        return HttpResponse.build().code(HttpStatusCode.SUCCESS).data("");
    }

}
