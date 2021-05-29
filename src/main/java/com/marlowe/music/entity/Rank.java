package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 排名表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲列表id
     */
    private Long songListId;

    /**
     * 用户id
     */
    private Long consumerId;

    /**
     * 分数
     */
    private Integer score;


}
