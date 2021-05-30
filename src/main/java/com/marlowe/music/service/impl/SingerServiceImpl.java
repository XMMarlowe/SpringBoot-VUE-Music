package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.mapper.SingerMapper;
import com.marlowe.music.service.ISingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 歌手表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {


    @Autowired
    private SingerMapper singerMapper;


    @Override
    public List<Singer> allSinger() {
        return singerMapper.selectList(null);
    }


    @Override
    public Singer addSinger(Singer singer) {
        singerMapper.insert(singer);
        return singer;
    }

    /**
     * 删除歌手
     *
     * @return
     */
    @Override
    public int deleteSinger(String id) {
        int delete = singerMapper.deleteById(id);
        return delete;
    }

    /**
     * 更新歌手信息
     *
     * @return
     */
    @Override
    public int updateSingerMsg(Singer singer) {
        return singerMapper.updateById(singer);
    }

    /**
     * 更新歌手头像
     *
     * @return
     */
    @Override
    public boolean updateSingerPic() {
        return false;
    }

    /**
     * 根据姓名查询歌手
     *
     * @return
     */
    @Override
    public List<Singer> findSingerByName(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<Singer> singerList = singerMapper.selectList(queryWrapper);
        return singerList;
    }

    /**
     * 根据性别查找歌手
     *
     * @return
     */
    @Override
    public List<Singer> findSingerBySex(int sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", sex);
        List<Singer> singerList = singerMapper.selectList(queryWrapper);
        return singerList;
    }
}
