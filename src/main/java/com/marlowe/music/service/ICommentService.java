package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-09
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment);

    /**
     * 通过id更新评论信息
     * @param comment
     * @return
     */
    boolean updateCommentMsg(Comment comment);

    /**
     * 通过id删除评论
     * @param id
     * @return
     */
    boolean deleteComment(Integer id);

    /**
     * 批量删除评论
     * @param ids
     * @return
     */
    boolean deleteComments(List<Integer> ids);


    /**
     * 分页查询所有评论
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Comment> allComments(int pageNo, int pageSize);

    /**
     * 分页查询指定歌曲id下的评论
     * @param songId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Comment> commentOfSongId(Integer songId,int pageNo, int pageSize);

    /**
     * 分页查询指定歌单id下的评论
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Comment> commentOfSongListId(Integer songListId,int pageNo, int pageSize);
}
