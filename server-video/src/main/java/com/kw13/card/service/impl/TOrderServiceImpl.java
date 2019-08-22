package com.kw13.card.service.impl;

import com.kw13.card.entity.TOrder;
import com.kw13.card.mapper.TOrderMapper;
import com.kw13.card.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购卡订单表 服务实现类
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
