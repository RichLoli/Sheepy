package com.macoloco.sheepy.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macoloco.sheepy.account.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Repository
public interface AccountDao extends BaseMapper<Account> {
}
