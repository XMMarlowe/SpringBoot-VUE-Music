package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.SongMapper;
import com.marlowe.music.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {



}
