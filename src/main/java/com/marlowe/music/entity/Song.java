package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌曲表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手id
     */
    private Integer singerId;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 发行时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 照片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    /**
     * url
     */
    private String url;


}
