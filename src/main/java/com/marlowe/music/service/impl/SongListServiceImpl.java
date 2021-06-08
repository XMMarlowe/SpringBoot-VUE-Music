package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return songListMapper.update(songList, null) > 0;
    }

    /**
     * 更新歌单封面
     *
     * @param songList
     * @return
     */
    @Override
    public boolean updateSongListImg(SongList songList) {
        return songListMapper.update(songList, null) > 0;
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

        List<SongList> songLists = songListMapper.selectList(null);
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
}
