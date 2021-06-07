package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
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
     * 删除歌单
     *
     * @param id
     * @return
     */
    boolean deleteSongList(Integer id);

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
     * 根据歌单风格模糊查询歌单
     *
     * @param style
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<SongList> findSongListByStyle(String style, int pageNo, int pageSize);


}
