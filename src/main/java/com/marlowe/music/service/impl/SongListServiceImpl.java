package com.marlowe.music.service.impl;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.mapper.SongListMapper;
import com.marlowe.music.service.ISongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements ISongListService {

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean addSongList(SongList songList) {
        return false;
    }

    /**
     * 更新歌单信息
     *
     * @param songList
     * @return
     */
    @Override
    public boolean updateSongListMsg(SongList songList) {
        return false;
    }

    /**
     * 更新歌单封面
     *
     * @param songList
     * @return
     */
    @Override
    public boolean updateSongListImg(SongList songList) {
        return false;
    }

    /**
     * 删除歌单
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSongList(Integer id) {
        return false;
    }

    /**
     * 分页查询所有歌单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SongList> allSongList(int pageNo, int pageSize) {
        return null;
    }

    /**
     * 根据歌单题目模糊查询歌单
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SongList> findSongListByTitle(String title, int pageNo, int pageSize) {
        return null;
    }

    /**
     * 根据歌单风格模糊查询歌单
     *
     * @param style
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SongList> findSongListByStyle(String style, int pageNo, int pageSize) {
        return null;
    }
}
