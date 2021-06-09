package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.music.entity.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 歌手表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
public interface ISingerService extends IService<Singer> {

    /**
     * 查询所有歌手
     *
     * @return
     */
    PageInfo<Singer> allSinger(int pageNo,int pageSize);

    /**
     * 添加歌手
     *
     * @return
     */
    Singer addSinger(Singer singer);


    /**
     * 删除歌手
     *
     * @return
     */
    int deleteSinger(String id);

    /**
     * 通过主键id更新歌手信息
     *
     * @return
     */
    int updateSingerMsg(Singer singer);


    /**
     * 更新歌手头像
     *
     * @return
     */
    boolean updateSingerPic();


    /**
     * 根据姓名查询歌手
     *
     * @return
     */
    PageInfo<Singer> findSingerByName(String name,int pageNo,int pageSize);


    /**
     * 根据性别查找歌手
     *
     * @return
     */
    PageInfo<Singer> findSingerBySex(int sex,int pageNo,int pageSize);

}
