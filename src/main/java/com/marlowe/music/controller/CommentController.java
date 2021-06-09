package com.marlowe.music.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Comment;
import com.marlowe.music.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-09
 */
@RestController
@Api(tags = "评论管理控制器")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @ApiOperation("添加评论")
    @PostMapping("add")
    public Result addComment(@RequestBody Comment comment) {
        boolean addComment = commentService.addComment(comment);
        if (addComment) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 分页查询所有评论
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询所有评论")
    @GetMapping("all/{pageNo}/{pageSize}")
    public Result<List<Comment>> allComments(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Comment> pageInfo = commentService.allComments(pageNo, pageSize);
        List<Comment> comments = pageInfo.getList();
        return Result.ok(comments);
    }

    /**
     * 分页查询指定歌曲id下的评论
     * @param songId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询指定歌曲id下的评论")
    @GetMapping("detail-songId/{songId}/{pageNo}/{pageSize}")
    public Result<List<Comment>> commentOfSongId(@PathVariable int songId, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Comment> pageInfo = commentService.commentOfSongId(songId, pageNo, pageSize);
        List<Comment> comments = pageInfo.getList();
        return Result.ok(comments);
    }

    /**
     * 分页查询指定歌单id下的评论
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询指定歌单id下的评论")
    @GetMapping("detail-songListId/{songListId}/{pageNo}/{pageSize}")
    public Result<List<Comment>> commentOfSongListId(@PathVariable int songListId, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Comment> pageInfo = commentService.commentOfSongListId(songListId, pageNo, pageSize);
        List<Comment> comments = pageInfo.getList();
        return Result.ok(comments);
    }

    /**
     * 通过id更新评论
     * @param comment
     * @return
     */
    @ApiOperation("通过id更新评论")
    @GetMapping("update")
    public Result updateCommentMsg(@RequestBody Comment comment) {
        boolean update = commentService.updateCommentMsg(comment);
        if (update) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 通过id删除评论
     * @param id
     * @return
     */
    @ApiOperation("通过id删除评论")
    @GetMapping("delete/{id}")
    public Result deleteComment(@PathVariable int id){
        boolean deleteComment = commentService.deleteComment(id);
        if (deleteComment) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     *
     * @param idList
     * @return
     */
    @ApiOperation("批量删除评论")
    @PostMapping("deletes")
    public Result deleteComments(@RequestBody List<Integer> idList){
        boolean deleteComment = commentService.deleteComments(idList);
        if (deleteComment) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }



}
