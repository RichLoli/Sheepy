package com.macoloco.sheepy.account.service;

import com.macoloco.sheepy.account.dao.AccountDao;
import com.macoloco.sheepy.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Service
@Slf4j
public class AccountService {

    @Resource
    AccountDao accountDao;

    public boolean regiserUser(Account account){
        //TODO 参数验证
        int insert = accountDao.insert(account);
        if (insert > 0) {
            return true;
        }
        log.warn("register User failure Affected Row : {}", insert);
        return false;
    }

}
