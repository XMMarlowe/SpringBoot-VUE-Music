package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.ListSong;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.music.entity.Singer;

import java.util.List;

/**
 * <p>
 * 歌曲对应歌单表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
public interface IListSongService extends IService<ListSong> {

    /**
     * 添加歌曲到歌单
     *
     * @param listSong
     * @return
     */
    boolean addListSong(ListSong listSong);

    /**
     * 更新歌单里面的歌曲信息
     *
     * @param listSong
     * @return
     */
    boolean updateListSongMsg(ListSong listSong);

    /**
     * 删除歌单里的歌曲
     *
     * @param songId
     * @return
     */
    boolean deleteListSong(Integer songId);


    /**
     * 分页查询歌单里面的所有歌曲
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<ListSong> allListSong(int pageNo, int pageSize);


    /**
     * 分页查询歌单里指定歌单ID的歌曲
     *
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<ListSong> findSongsBySongListId(Integer songListId, int pageNo, int pageSize);

}
