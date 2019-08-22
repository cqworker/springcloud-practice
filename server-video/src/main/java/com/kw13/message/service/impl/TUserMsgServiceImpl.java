package com.kw13.message.service.impl;

import com.kw13.message.entity.TUserMsg;
import com.kw13.message.mapper.TUserMsgMapper;
import com.kw13.message.service.ITUserMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * wx用户服务消息关系表 服务实现类
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Service
public class TUserMsgServiceImpl extends ServiceImpl<TUserMsgMapper, TUserMsg> implements ITUserMsgService {

}
