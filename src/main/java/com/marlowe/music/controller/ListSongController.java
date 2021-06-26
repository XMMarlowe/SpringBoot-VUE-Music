package com.marlowe.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.ListSong;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.service.IListSongService;
import com.marlowe.music.service.ISongListService;
import com.marlowe.music.service.ISongService;
import com.marlowe.music.service.impl.ListSongServiceImpl;
import com.marlowe.music.service.impl.SongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private IListSongService listSongService;

    @Autowired
    private ISongService songService;

    @Autowired
    private ISongListService songListService;

    /**
     * 添加歌曲到歌单中，并更新当前歌曲pic为歌单封面
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
            return Result.ok("该歌曲已在歌单中");
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
     * 删除指定歌单id里面的指定歌曲id,并更新歌单封面
     *
     * @param songId
     * @param songListId
     * @return
     */
    @ApiOperation("删除指定歌单id里面的指定歌曲id,并更新歌单封面")
    @PostMapping("delete/{songId}/{songListId}")
    public Result deleteListSongBySongIdAndSongListId(@PathVariable int songId, @PathVariable int songListId) {
        boolean deleteListSong = listSongService.deleteListSongBySongIdAndSongListId(songId, songListId);
        if (deleteListSong) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }


    /**
     * 分页查询歌单表里指定歌单ID的所有歌曲
     *
     * @param songListId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询歌单表里指定歌单ID的所有歌曲")
    @GetMapping("detail/{songListId}/{pageNo}/{pageSize}")
    public Result<List<Song>> findSongsBySongListId(@PathVariable int songListId, @PathVariable int pageNo, @PathVariable int pageSize) {
        JSONObject jsonObject = new JSONObject();

        PageInfo<ListSong> pageInfo = listSongService.findSongsBySongListId(songListId, pageNo, pageSize);
        List<ListSong> songIds = pageInfo.getList();

        // 得到歌单里面的所有歌曲id，再根据歌曲id查询歌曲信息，返回给前端
        List<Song> songs = new ArrayList<>();
        for (ListSong songId : songIds) {
            Song song = songService.findSongById(songId.getSongId());
            songs.add(song);
        }


        // 通过歌单id查询歌单信息并返回
        SongList songListInfo = songListService.findById(songListId);

        jsonObject.put("songs", songs);
        jsonObject.put("songListInfo", songListInfo);
        return Result.ok(jsonObject);
    }


}
