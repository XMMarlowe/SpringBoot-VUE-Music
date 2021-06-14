package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Comment;
import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.CommentMapper;
import com.marlowe.music.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;


    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    /**
     * 通过id更新评论信息
     *
     * @param comment
     * @return
     */
    @Override
    public boolean updateCommentMsg(Comment comment) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", comment.getId());
        int update = commentMapper.update(comment, queryWrapper);
        return update > 0;
    }

    /**
     * 通过id删除评论
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteComment(Integer id) {
        int delete = commentMapper.deleteById(id);
        return delete > 0;
    }

    /**
     * 批量删除评论
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteComments(List<Integer> ids) {
        int deleteBatchIds = commentMapper.deleteBatchIds(ids);
        return deleteBatchIds > 0;
    }

    /**
     * 分页查询所有评论
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Comment> allComments(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");

        List<Comment> comments = commentMapper.selectList(queryWrapper);

        PageInfo<Comment> pageInfo = new PageInfo(comments);

        return pageInfo;
    }

    /**
     * 分页查询指定歌曲id下的评论
     *
     * @param songId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Comment> commentOfSongId(Integer songId, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id", songId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        PageInfo<Comment> pageInfo = new PageInfo(comments);
        return pageInfo;
    }

    /**
     * 分页查询指定歌单id下的评论
     *
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Comment> commentOfSongListId(Integer songListId, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id", songListId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        PageInfo<Comment> pageInfo = new PageInfo(comments);
        return pageInfo;
    }
}
