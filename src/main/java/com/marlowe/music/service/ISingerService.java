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
     * 根据歌手id获得歌手信息
     * @param singerId
     * @return
     */
    Singer findBySingerId(int singerId);

    /**
     * 添加歌手
     *
     * @return
     */
    boolean addSinger(Singer singer);


    /**
     * 通过id删除歌手
     *
     * @return
     */
    boolean deleteSinger(String id);

    /**
     * 批量删除歌手
     * @param ids
     * @return
     */
    boolean deleteSingers(List<Integer> ids);

    /**
     * 通过主键id更新歌手信息
     *
     * @return
     */
    boolean updateSingerMsg(Singer singer);


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

    /**
     * 获得歌手总数量
     * @return
     */
    int singerCount();

    /**
     * 根据性别获得歌曲数量
     * @param sex
     * @return
     */
    int singerCountOfSex(int sex);


    /**
     * 根据歌手地区获得歌曲数量
     * @param location
     * @return
     */
    int singerCountOfLocation(String location);

}
