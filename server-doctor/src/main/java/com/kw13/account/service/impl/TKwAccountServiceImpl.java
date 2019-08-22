package com.kw13.account.service.impl;

import com.kw13.account.entity.TKwAccount;
import com.kw13.account.mapper.TKwAccountMapper;
import com.kw13.account.service.ITKwAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * kw平台用户表 服务实现类
 * </p>
 *
 * @author cq
 * @since 2019-08-21
 */
@Service
public class TKwAccountServiceImpl extends ServiceImpl<TKwAccountMapper, TKwAccount> implements ITKwAccountService {

}
