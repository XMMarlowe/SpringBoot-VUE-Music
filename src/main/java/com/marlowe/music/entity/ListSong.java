package com.marlowe.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌曲对应歌单表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 歌单id
     */
    private Integer songListId;


}
