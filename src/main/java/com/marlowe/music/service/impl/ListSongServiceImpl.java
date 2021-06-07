package com.marlowe.music.service.impl;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.mapper.ListSongMapper;
import com.marlowe.music.service.IListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲对应歌单表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements IListSongService {

    /**
     * 添加歌曲到歌单
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean addListSong(ListSong listSong) {
        return false;
    }

    /**
     * 更新歌单里面的歌曲信息
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return false;
    }

    /**
     * 删除歌单里的歌曲
     *
     * @param songId
     * @return
     */
    @Override
    public boolean deleteListSong(Integer songId) {
        return false;
    }

    /**
     * 分页查询歌单里面的所有歌曲
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ListSong> allListSong(int pageNo, int pageSize) {
        return null;
    }

    /**
     * 分页查询歌单里指定歌单ID的歌曲
     *
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ListSong> findSongsBySongListId(Integer songListId, int pageNo, int pageSize) {
        return null;
    }
}
