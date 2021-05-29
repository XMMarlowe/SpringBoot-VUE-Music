package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌曲列表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SongList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 列表名
     */
    private String title;

    /**
     * 照片
     */
    private String pic;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 风格
     */
    private String style;


}
