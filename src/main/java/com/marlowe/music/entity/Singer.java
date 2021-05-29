package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌手表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 照片
     */
    private String pic;

    /**
     * 生日
     */
    private LocalDateTime birth;

    /**
     * 地区
     */
    private String location;

    /**
     * 简介
     */
    private String introduction;


}
