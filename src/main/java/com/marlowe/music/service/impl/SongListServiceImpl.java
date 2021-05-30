package com.marlowe.music.service.impl;

import com.marlowe.music.entity.SongList;
import com.marlowe.music.mapper.SongListMapper;
import com.marlowe.music.service.ISongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲列表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements ISongListService {

}
