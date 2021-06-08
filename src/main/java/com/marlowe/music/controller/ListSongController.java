package com.marlowe.music.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.service.impl.ListSongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 歌曲对应歌单表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/listSong")
@Api(tags = "歌曲对应歌单控制器")
public class ListSongController {


    @Autowired
    private ListSongServiceImpl listSongService;

    /**
     * 添加歌曲到歌单中
     *
     * @param listSong
     * @return
     */
    @ApiOperation("添加歌曲到歌单中")
    @PostMapping("add")
    public Result addListSong(@RequestBody ListSong listSong) {
        boolean addListSong = listSongService.addListSong(listSong);
        if (addListSong) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 更新歌单里面的歌曲信息
     *
     * @param listSong
     * @return
     */
    @ApiOperation("更新歌单里面的歌曲信息")
    @PostMapping("update")
    public Result updateListSongMsg(@RequestBody ListSong listSong) {
        boolean updateListSongMsg = listSongService.updateListSongMsg(listSong);
        if (updateListSongMsg) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 删除歌单里指定id的歌曲
     *
     * @param id
     * @return
     */
    @ApiOperation("更新歌单里面的歌曲信息")
    @PostMapping("delete/{id}")
    public Result deleteListSong(@PathVariable int id) {
        boolean deleteListSong = listSongService.deleteListSong(id);
        if (deleteListSong) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }


    /**
     * 分页查询歌单里面的所有歌曲
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询歌单里面的所有歌曲")
    @GetMapping("allListSong/{pageNo}/{pageSize}")
    public Result<List<ListSong>> allListSong(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<ListSong> pageInfo = listSongService.allListSong(pageNo, pageSize);
        List<ListSong> listSongs = pageInfo.getList();
        return Result.ok(listSongs);
    }


    /**
     * 分页查询歌单表里指定歌单ID的所有歌曲
     *
     * @param id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询歌单表里指定歌单ID的所有歌曲")
    @GetMapping("detail/{id}/{pageNo}/{pageSize}")
    public Result<List<ListSong>> findSongsBySongListId(@PathVariable int id, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<ListSong> pageInfo = listSongService.findSongsBySongListId(id, pageNo, pageSize);
        List<ListSong> listSongs = pageInfo.getList();

        // 得到歌单里面的所有歌曲id，再根据歌曲id查询歌曲信息，返回给前端

        // TODO:: 完善
        return Result.ok(listSongs);
    }


}
