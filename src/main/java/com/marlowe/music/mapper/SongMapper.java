package com.marlowe.music.mapper;

import com.marlowe.music.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 歌曲表 Mapper 接口
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
public interface SongMapper extends BaseMapper<Song> {


//    /**
//     * 根据歌手名查询此歌手的所有歌曲
//     * @param name
//     * @return
//     */
//    List<Song> findSongBySingerName(String name);
}
