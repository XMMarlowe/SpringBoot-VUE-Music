package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 歌曲表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲id
     */
    private String songId;

    /**
     * 歌手id
     */
    private String singerId;

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


    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌手名
     */
    private String singerName;
    /**
     * 简介
     */
    private String introduction;

    /**
     * 是否下载
     */
    private int isDownload;

    /**
     * 发行时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updateTime;


}
