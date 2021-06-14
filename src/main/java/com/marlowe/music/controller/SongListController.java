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
 * 歌单表 前端控制器,在此类中，只要发起查询歌单请求，都需要更新歌单封面，更新逻辑：采用当前歌单的第一首歌曲作为歌单封面
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
    private ISongListService songListService;

    @Autowired
    private IListSongService listSongService;

    @Autowired
    private ISongService songService;

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

    /**
     * 批量删除歌单
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除歌单")
    @GetMapping("deletes")
    public Result deleteSongLists(@RequestBody List<Integer> idList) {
        boolean deleteSongLists = songListService.deleteSongLists(idList);
        if (deleteSongLists) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    @ApiOperation(value = "随机获得n个歌单")
    @GetMapping("getRandomSongList/{num}")
    public Result<List<SongList>> getRandomSongList(@PathVariable int num) {

        List<SongList> res = new ArrayList<>();
        //  先查询出所有歌单的id
        PageInfo<SongList> pageInfo = songListService.allSongList(1, 100000);
        List<SongList> list = pageInfo.getList();

        // 获得歌单列表大小，生成索引随机数
        int size = list.size();

        if (size < num) {
            return Result.ok("当前歌单数不足，请调小请求个数");
        }

        // 获得不重复随机数索引
        List<Integer> songListIdList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            int nextInt = new Random().nextInt(size);
            if (!songListIdList.contains(nextInt)) {
                songListIdList.add(nextInt);
            }else{
                i--;
            }
        }

        for (int i = 0; i < songListIdList.size(); i++) {
            res.add(list.get(songListIdList.get(i)));
        }

        return Result.ok(res);
    }

    /**
     * 分页查询所有歌单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询所有歌单")
    @GetMapping("allSongList/{pageNo}/{pageSize}")
    public Result<List<SongList>> allSongList(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<SongList> pageInfo = songListService.allSongList(pageNo, pageSize);
        List<SongList> songLists = pageInfo.getList();

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

    /**
     * 根据userId查询该用户创建的所有歌单,并且以歌单里面的第一首歌曲封面作为歌单封面
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据userId查询该用户创建的所有歌单")
    @GetMapping("detail-userId/{userId}")
    public Result<List<SongList>> findListSongByUserId(@PathVariable int userId) {
        List<SongList> songLists = songListService.findSongListByUserId(userId);
        return Result.ok(songLists);

    }

    /**
     * 获得歌单的数量
     *
     * @return
     */
    @ApiOperation("获得歌单的数量")
    @GetMapping("count")
    public Result songListCount() {
        int songListCount = songListService.songListCount();
        return Result.ok(songListCount);
    }


}



