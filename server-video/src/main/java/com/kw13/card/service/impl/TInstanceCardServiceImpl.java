package com.kw13.card.service.impl;

import com.kw13.card.entity.TInstanceCard;
import com.kw13.card.mapper.TInstanceCardMapper;
import com.kw13.card.service.ITInstanceCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储购买或领取的卡实例 服务实现类
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Service
public class TInstanceCardServiceImpl extends ServiceImpl<TInstanceCardMapper, TInstanceCard> implements ITInstanceCardService {

}
