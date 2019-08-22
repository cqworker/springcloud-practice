package com.kw13.account.service.impl;

import com.kw13.account.entity.TWxAccount;
import com.kw13.account.mapper.TWxAccountMapper;
import com.kw13.account.service.ITWxAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录C端的微信用户账号表 服务实现类
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Service
public class TWxAccountServiceImpl extends ServiceImpl<TWxAccountMapper, TWxAccount> implements ITWxAccountService {

}
