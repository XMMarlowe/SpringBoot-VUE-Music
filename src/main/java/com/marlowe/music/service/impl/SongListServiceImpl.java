package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.mapper.SongListMapper;
import com.marlowe.music.service.ISongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean addSongList(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * 更新歌单信息
     *
     * @param songList
     * @return
     */
    @Override
    public boolean updateSongListMsg(SongList songList) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", songList.getId());
        return songListMapper.update(songList, queryWrapper) > 0;
    }

    /**
     * 更新歌单封面
     *
     * @param songList
     * @return
     */
    @Override
    public boolean updateSongListImg(SongList songList) {

        return true;
    }

    /**
     * 删除歌单
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除歌单
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteSongLists(List<Integer> ids) {
        return songListMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过id获取歌单信息
     *
     * @param id
     * @return
     */
    @Override
    public SongList findById(int id) {
        // 在list_song表中查询所有对应歌单的所有歌曲
        SongList songList = songListMapper.selectById(id);
        return songList;
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
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<SongList> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        PageInfo<SongList> pageInfo = new PageInfo(songLists);
        return pageInfo;
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
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title);
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        PageInfo<SongList> pageInfo = new PageInfo(songLists);
        return pageInfo;
    }

    /**
     * 根据歌单风格查询歌单
     *
     * @param style
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SongList> findSongListByStyle(String style, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style", style);
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        PageInfo<SongList> pageInfo = new PageInfo(songLists);
        return pageInfo;
    }

    /**
     * 根据userId查询该用户创建的所有歌单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SongList> findSongListByUserId(int userId) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        return songLists;
    }

    /**
     * 获得歌单的数量
     *
     * @return
     */
    @Override
    public int songListCount() {
        return songListMapper.selectCount(null);
    }
}
