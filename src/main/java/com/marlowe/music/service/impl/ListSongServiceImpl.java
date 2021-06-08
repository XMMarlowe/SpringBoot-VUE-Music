package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.mapper.ListSongMapper;
import com.marlowe.music.service.IListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 添加歌曲到歌单
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean addListSong(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    /**
     * 更新歌单里面的歌曲信息
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.update(listSong, null) > 0;
    }

    /**
     * 删除歌单里的歌曲
     *
     * @param songId
     * @return
     */
    @Override
    public boolean deleteListSong(Integer songId) {
        return listSongMapper.deleteById(songId) > 0;
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

        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<ListSong> listSongs = listSongMapper.selectList(null);

        PageInfo<ListSong> pageInfo = new PageInfo(listSongs);
        return pageInfo;
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
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id", songListId);
        List<ListSong> songs = listSongMapper.selectList(queryWrapper);
        PageInfo<ListSong> pageInfo = new PageInfo(songs);
        return pageInfo;
    }
}
