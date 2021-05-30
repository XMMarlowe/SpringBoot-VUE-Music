package com.marlowe.music.service.impl;

import com.marlowe.music.entity.ListSong;
import com.marlowe.music.mapper.ListSongMapper;
import com.marlowe.music.service.IListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements IListSongService {

}
