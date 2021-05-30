package com.marlowe.music.service;

import com.marlowe.music.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 歌曲表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
public interface ISongService extends IService<Song> {

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    boolean addSong (Song song);

    /**
     * 更新歌曲信息
     * @param song
     * @return
     */
    boolean updateSongMsg(Song song);

    /**
     * 更新歌曲url
     * @param song
     * @return
     */
    boolean updateSongUrl(Song song);

    /**
     * 更新歌曲图片
     * @param song
     * @return
     */
    boolean updateSongPic(Song song);

    /**
     * 删除歌曲
     * @param id
     * @return
     */
    boolean deleteSong(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    List<Song> allSong();

    /**
     * 根据歌手id查询歌曲
     * @param singerId
     * @return
     */
    List<Song> findSongBySingerId(Integer singerId);

    /**
     * 根据id查找歌曲
     * @param id
     * @return
     */
    List<Song> findSongById(Integer id);

    /**
     * 根据歌手名字查找歌曲
     * @param name
     * @return
     */
    List<Song> findSongBySingerName(String name);

    /**
     * 根据歌曲名查找歌曲
     * @param name
     * @return
     */
    List<Song> findSongByName(String name);
}
