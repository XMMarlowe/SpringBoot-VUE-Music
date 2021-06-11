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
     * 更新歌曲播放次数
     *
     * @param song
     * @return
     */
    boolean updateSongPlayCount(Song song);


    /**
     * 更新歌曲图片
     *
     * @param song
     * @return
     */
    boolean updateSongPic(Song song);

    /**
     * 根据id删除歌曲
     *
     * @param id
     * @return
     */
    boolean deleteSong(Integer id);

    /**
     * 批量删除歌曲
     *
     * @param ids
     * @return
     */
    boolean deleteSongs(List<Integer> ids);


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
     * 搜索框根据歌曲名字模糊搜索
     *
     * @param name
     * @return
     */
    List<Song> findByNameLike(String name);

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

    /**
     * 查询歌曲总数
     *
     * @return
     */
    int songCount();


    /**
     * 查询播放次数前n的歌曲
     *
     * @param count
     * @return
     */
    List<Song> songOrderByPlayCount(int count);

}
