package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.SongMapper;
import com.marlowe.music.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 添加歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean addSong(Song song) {
        return false;
    }

    /**
     * 更新歌曲信息
     *
     * @param song
     * @return
     */
    @Override
    public boolean updateSongMsg(Song song) {
        return false;
    }

    /**
     * 更新歌曲url
     *
     * @param song
     * @return
     */
    @Override
    public boolean updateSongUrl(Song song) {
        return false;
    }

    /**
     * 更新歌曲图片
     *
     * @param song
     * @return
     */
    @Override
    public boolean updateSongPic(Song song) {
        return false;
    }

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSong(Integer id) {
        return false;
    }

    /**
     * 查询所有歌曲
     *
     * @return
     */
    @Override
    public List<Song> allSong() {
        return null;
    }

    /**
     * 根据歌手id查询歌曲
     *
     * @param singerId
     * @return
     */
    @Override
    public List<Song> findSongBySingerId(Integer singerId) {
        return null;
    }

    /**
     * 根据id查找歌曲
     *
     * @param id
     * @return
     */
    @Override
    public List<Song> findSongById(Integer id) {
        return null;
    }

    /**
     * 根据歌手名字查找歌曲
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> findSongBySingerName(String name) {
        return null;
    }

    /**
     * 根据歌曲名查找歌曲
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> findSongByName(String name) {
        return null;
    }
}
