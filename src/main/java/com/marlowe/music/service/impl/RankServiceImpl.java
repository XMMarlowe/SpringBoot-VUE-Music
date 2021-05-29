package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Rank;
import com.marlowe.music.mapper.RankMapper;
import com.marlowe.music.service.IRankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 排名表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Service
public class RankServiceImpl extends ServiceImpl<RankMapper, Rank> implements IRankService {

}
