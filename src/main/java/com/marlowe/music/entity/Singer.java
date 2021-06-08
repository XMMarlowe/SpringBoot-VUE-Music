package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 歌手表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手id
     */
    private String singerId;


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
    private Date birth;

    /**
     * 地区
     */
    private String location;

    /**
     * 发行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updateTime;

    /**
     * 简介
     */
    private String introduction;

    public Singer(String singerId, String name) {
        this.singerId = singerId;
        this.name = name;
    }

    public Singer(String singerId, String name, int sex, String pic, String location, String introduction) {
        this.singerId = singerId;
        this.name = name;
        this.sex = sex;
        this.pic = pic;
        this.location = location;
        this.introduction = introduction;
    }
}
