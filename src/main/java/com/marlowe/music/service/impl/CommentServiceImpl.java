package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Comment;
import com.marlowe.music.mapper.CommentMapper;
import com.marlowe.music.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
