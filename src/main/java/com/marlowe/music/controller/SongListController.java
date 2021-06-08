package com.marlowe.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.SongList;
import com.marlowe.music.service.impl.ListSongServiceImpl;
import com.marlowe.music.service.impl.SongListServiceImpl;
import com.marlowe.music.service.impl.SongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 歌单表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/songList")
@Api(tags = "歌单管理控制类")
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;

    @Autowired
    private ListSongServiceImpl listSongService;

    @Autowired
    private SongServiceImpl songService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:/Users/hongweiyin/Documents/github-workspace/music-website/music-server/img/songListPic/");
        }
    }

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    @ApiOperation(value = "添加歌单")
    @PostMapping(value = "add")
    public Result addSongList(@RequestBody SongList songList) {
        boolean addSongList = songListService.addSongList(songList);
        if (addSongList) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 更新歌单信息
     *
     * @param songList
     * @return
     */
    @ApiOperation(value = "更新歌单信息")
    @PostMapping("update")
    public Result updateSongListMsg(@RequestBody SongList songList) {
        boolean updateSongListMsg = songListService.updateSongListMsg(songList);
        if (updateSongListMsg) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 更新歌单封面
     *
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新歌单封面")
    @PostMapping("/img/update")
    public Result updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return null;
    }

    /**
     * 根据id删除歌单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除歌单")
    @GetMapping("delete/{id}")
    public Result deleteSongList(@PathVariable int id) {
        boolean deleteSongList = songListService.deleteSongList(id);
        if (deleteSongList) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    @ApiOperation(value = "随机获得n个歌单")
    @GetMapping("getRandomSongList/{num}")
    public Result<List<SongList>> getRandomSongList(@PathVariable int num) {

        List<SongList> res = new ArrayList<>();
        //  先查询出歌单总数
        int allSongListCount = songListService.getAllSongList();
        // 获得随机列表
        List<Integer> songListIdList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int nextInt = new Random().nextInt(allSongListCount);
            if (!songListIdList.contains(nextInt)) {
                songListIdList.add(nextInt);
            }
        }

        // 根据随机列表，返回歌单信息，包含歌单的封面，封面为具体歌单列表中的第一首歌曲
        for (Integer songListId : songListIdList) {
            // 通过id获得歌单信息
            SongList songListInfo = songListService.findById(songListId);
            // 通过歌单信息获取歌单里面的所有歌曲。直接查询歌单里面最后添加的一首歌曲，如果没有歌，则用默认封面，有歌曲，就用当前歌曲的图片
            int lastSongIdBySongListId = listSongService.findLastSongIdBySongListId(songListInfo.getId());

            // 如果歌单里面没有歌曲，就用默认的图片，有歌曲，就取出最后一首歌曲的pic作为封面
            if (lastSongIdBySongListId != -1) {
                String songByIdPic = songService.findSongById(lastSongIdBySongListId).getPic();
                songListInfo.setPic(songByIdPic);
            }
            boolean b = songListService.updateSongListImg(songListInfo);
            res.add(songListInfo);
        }
        return Result.ok(res);
    }

    /**
     * 分页查询所有歌单，并且得到第一首歌的图片作为封面
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询所有歌单，并且得到第一首歌的图片作为封面")
    @GetMapping("allSongList/{pageNo}/{pageSize}")
    public Result<List<SongList>> allSongList(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<SongList> pageInfo = songListService.allSongList(pageNo, pageSize);
        List<SongList> songLists = pageInfo.getList();

        // TODO:: 完善   分页查询所有歌单，并且得到第一首歌的图片作为封面
        return Result.ok(songLists);
    }

    /**
     * 根据歌单题目模糊查询歌单
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据歌单题目模糊查询歌单")
    @GetMapping("title/detail/{title}/{pageNo}/{pageSize}")
    public Result<List<SongList>> findSongListByTitle(@PathVariable String title, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<SongList> pageInfo = songListService.findSongListByTitle(title, pageNo, pageSize);
        List<SongList> songLists = pageInfo.getList();
        return Result.ok(songLists);
    }

    /**
     * 根据歌单风格模糊查询歌单
     *
     * @param style
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据歌单风格模糊查询歌单")
    @GetMapping("style/detail/{style}/{pageNo}/{pageSize}")
    public Result<List<SongList>> findSongListByStyle(@PathVariable String style, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<SongList> pageInfo = songListService.findSongListByStyle(style, pageNo, pageSize);
        List<SongList> songLists = pageInfo.getList();
        return Result.ok(songLists);
    }

}



