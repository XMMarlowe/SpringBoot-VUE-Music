package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Singer;
import com.marlowe.music.mapper.SingerMapper;
import com.marlowe.music.service.ISingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌手表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {

}
