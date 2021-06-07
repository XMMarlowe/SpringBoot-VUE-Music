package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.SongMapper;
import com.marlowe.music.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 歌曲表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

    @Autowired
    private SongMapper songMapper;


    /**
     * 添加歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean addSong(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 更新歌曲信息，只允许修改歌词
     *
     * @param song
     * @return
     */
    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.update(song, null) > 0;
    }

    /**
     * 更新歌曲图片
     *
     * @param song
     * @return
     */
    @Override
    public boolean updateSongPic(Song song) {
        return songMapper.update(song, null) > 0;
    }

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteById(id) > 0;
    }

    /**
     * 查询所有歌曲
     *
     * @return
     */
    @Override
    public PageInfo<Song> allSong(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<Song> songs = songMapper.selectList(null);

        PageInfo<Song> pageInfo = new PageInfo(songs);
        return pageInfo;
    }

    /**
     * 根据歌手id查询此歌手的所有歌曲
     *
     * @param singerId
     * @return
     */
    @Override
    public PageInfo<Song> findSongBySingerId(Integer singerId, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id", singerId);
        List<Song> songs = songMapper.selectList(queryWrapper);
        PageInfo<Song> pageInfo = new PageInfo(songs);
        return pageInfo;
    }

    /**
     * 根据歌手名字查找此歌手下面的所有歌曲
     *
     * @param name
     * @return
     */
    @Override
    public PageInfo<Song> findSongBySingerName(String name, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("singer_name", name);
        List<Song> songs = songMapper.selectList(queryWrapper);
        PageInfo<Song> pageInfo = new PageInfo(songs);
        return pageInfo;
    }

    /**
     * 根据id查找歌曲
     *
     * @param id
     * @return
     */
    @Override
    public Song findSongById(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id", id);
        return songMapper.selectOne(queryWrapper);
    }

    /**
     * 根据歌曲名查找歌曲
     *
     * @param name
     * @return
     */
    @Override
    public PageInfo<Song> findSongByName(String name, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<Song> songs = songMapper.selectList(queryWrapper);
        PageInfo<Song> pageInfo = new PageInfo(songs);
        return pageInfo;
    }
}
