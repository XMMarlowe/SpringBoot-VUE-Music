package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 歌单表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
public interface ISongListService extends IService<SongList> {

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    boolean addSongList(SongList songList);

    /**
     * 更新歌单信息
     *
     * @param songList
     * @return
     */
    boolean updateSongListMsg(SongList songList);

    /**
     * 更新歌单封面
     *
     * @param songList
     * @return
     */
    boolean updateSongListImg(SongList songList);

    /**
     * 根据主键id删除歌单
     *
     * @param id
     * @return
     */
    boolean deleteSongList(Integer id);

    /**
     * 批量删除歌单
     * @param ids
     * @return
     */
    boolean deleteSongLists(List<Integer> ids);

    /**
     * 通过id获取歌单信息
     * @param id
     * @return
     */
    SongList findById(int id);



    /**
     * 分页查询所有歌单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<SongList> allSongList(int pageNo, int pageSize);

    /**
     * 根据歌单题目模糊查询歌单
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<SongList> findSongListByTitle(String title, int pageNo, int pageSize);

    /**
     * 根据歌单风格查询歌单
     *
     * @param style
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<SongList> findSongListByStyle(String style, int pageNo, int pageSize);

    /**
     * 根据userId查询该用户创建的所有歌单
     * @param userId
     * @return
     */
    List<SongList> findSongListByUserId(int userId);

    /**
     * 获得歌单的数量
     * @return
     */
    int songListCount();


}
