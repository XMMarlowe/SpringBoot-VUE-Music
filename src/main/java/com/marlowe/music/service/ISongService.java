package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
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
     *
     * @param song
     * @return
     */
    boolean addSong(Song song);

    /**
     * 根据主键id更新歌曲信息，只允许修改歌词
     *
     * @param song
     * @return
     */
    boolean updateSongMsg(Song song);


    /**
     * 更新歌曲图片
     *
     * @param song
     * @return
     */
    boolean updateSongPic(Song song);

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    boolean deleteSong(Integer id);

    /**
     * 查询所有歌曲
     *
     * @return
     */
    PageInfo<Song> allSong(int pageNo, int pageSize);

    /**
     * 根据歌手id查询此歌手的所有歌曲
     *
     * @param singerId
     * @return
     */
    PageInfo<Song> findSongBySingerId(Integer singerId, int pageNo, int pageSize);

    /**
     * 根据歌手名字查找此歌手下面的所有歌曲
     *
     * @param name
     * @return
     */
    PageInfo<Song> findSongBySingerName(String name, int pageNo, int pageSize);

    /**
     * 根据id查找歌曲
     *
     * @param id
     * @return
     */
    Song findSongById(Integer id);


    /**
     * 根据歌曲名查找歌曲
     *
     * @param name
     * @return
     */
    PageInfo<Song> findSongByName(String name, int pageNo, int pageSize);
}
