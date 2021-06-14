package com.marlowe.music.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.Song;
import com.marlowe.music.service.ISingerService;
import com.marlowe.music.service.ISongService;
import com.marlowe.music.service.impl.SingerServiceImpl;
import com.marlowe.music.service.impl.SongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * <p>
 * 歌曲表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@RestController
@Api(tags = "歌曲管理控制类")
@RequestMapping("/song")
@Slf4j
public class SongController {

    @Autowired
    private ISongService songService;

    @Autowired
    private ISingerService singerService;


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:D:\\IDE_Project\\JavaLearning\\SpringBoot-VUE-Music\\img\\songPic");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D:\\IDE_Project\\JavaLearning\\SpringBoot-VUE-Music\\song");
        }
    }


    /**
     * 添加歌曲
     *
     * @return
     */
    @ApiOperation(value = "添加歌曲")
    @PostMapping("add")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public Result addSong(@RequestBody Song song) {
        boolean addSong = songService.addSong(song);
        if (addSong) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 查询所有歌曲
     *
     * @return
     */
    @ApiOperation(value = "查询所有歌曲")
    @GetMapping("allSong/{pageNo}/{pageSize}")
    public Result<List<Song>> allSong(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Song> pageInfo = songService.allSong(pageNo, pageSize);
        List<Song> songs = pageInfo.getList();
        return Result.ok(songs);
    }

    /**
     * 根据歌曲id查找歌曲，返回歌曲详细信息，
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找歌曲歌曲")
    @GetMapping("detail/{id}")
    public Result<Song> findSongById(@PathVariable("id") int id) {
        Song song = songService.findSongById(id);
        return Result.ok(song);
    }


    /**
     * 根据歌曲id下载歌曲到本地
     *
     * @return
     */
    public Result<Song> downloadSongById(int id) {
        return null;
    }

    /**
     * 查询指定歌手ID的所有歌曲
     *
     * @param singerId
     * @return
     */
    @ApiOperation(value = "查询指定歌手ID的所有歌曲")
    @GetMapping("/singer-id/detail/{singerId}/{pageNo}/{pageSize}")
    public Result<Song> findSongsBySingerId(@PathVariable("singerId") int singerId, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Song> pageInfo = songService.findSongBySingerId(singerId, pageNo, pageSize);
        List<Song> songs = pageInfo.getList();
        return Result.ok(songs);
    }


    /**
     * 返回指定歌手名的所有歌曲
     *
     * @param singerName
     * @return
     */
    @ApiOperation(value = "查询指定歌手名的所有歌曲")
    @GetMapping("/singer-name/detail/{singerName}/{pageNo}/{pageSize}")
    public Result<List<Song>> findSongsBySingerName(@PathVariable String singerName, @PathVariable int pageNo, @PathVariable int pageSize) {
        log.info("singerName = " + singerName);
        PageInfo<Song> pageInfo = songService.findSongBySingerName(singerName, pageNo, pageSize);
        List<Song> songs = pageInfo.getList();
        return Result.ok(songs);
    }

    /**
     * 返回指定歌曲名的歌曲
     *
     * @param songName
     * @return
     */
    @ApiOperation(value = "查询指定歌曲名的歌曲")
    @GetMapping("/song-name/detail/{songName}/{pageNo}/{pageSize}")
    public Result<List<Song>> findSongBySongName(@PathVariable String songName, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Song> pageInfo = songService.findSongByName(songName, pageNo, pageSize);
        List<Song> songs = pageInfo.getList();
        return Result.ok(songs);
    }


    /**
     * 删除歌曲
     *
     * @return
     */
    @ApiOperation(value = "根据id删除歌曲")
    @GetMapping("delete/{id}")
    @RequiresRoles("root")
    public Result deleteSong(@PathVariable int id) {
        boolean deleteSong = songService.deleteSong(id);
        if (deleteSong) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 批量删除歌曲
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除歌曲")
    @GetMapping("deletes")
    @RequiresRoles("root")
    public Result deleteSongs(@RequestBody List<Integer> idList) {
        boolean deleteSongs = songService.deleteSongs(idList);
        if (deleteSongs) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据主键id更新歌曲信息，只允许修改歌词
     *
     * @return
     */
    @ApiOperation(value = "根据主键id更新歌曲信息，只允许修改歌词")
    @PostMapping("update")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public Result updateSongMsg(@RequestBody Song song) {
        boolean updateSongMsg = songService.updateSongMsg(song);
        if (updateSongMsg) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 获得歌曲总数
     *
     * @return
     */
    @ApiOperation(value = "获得歌曲总数")
    @PostMapping("count")
    public Result songCount() {
        int songCount = songService.songCount();
        return Result.ok(songCount);
    }

    /**
     * 播放并更新播放次数  次数+1
     *
     * @param song
     * @return
     */
    @ApiOperation(value = "播放并更新播放次数 次数+1")
    @PostMapping("play")
    public Result playAndUpdatePlayCount(@RequestBody Song song) {
        // 更新播放次数
        boolean updateSongPlayCount = songService.updateSongPlayCount(song);
        // 查询更新后的数据并返回
        Song songById = songService.findSongById(song.getId());
        return Result.ok(songById);
    }


    /**
     * 搜索框根据歌曲名字模糊搜索
     *
     * @param keyWord
     * @return
     */
    @ApiOperation(value = "搜索框根据歌曲名字模糊搜索")
    @PostMapping("search/{keyWord}")
    public Result<JSONObject> searchSuggestion(@PathVariable String keyWord) {
        JSONObject jsonObject = new JSONObject();
        List<Song> byNameLike = songService.findByNameLike(keyWord);
        List<Singer> byNameLike1 = singerService.findByNameLike(keyWord);
        jsonObject.put("songRes", byNameLike);
        jsonObject.put("singerRes", byNameLike1);
        return Result.ok(jsonObject);
    }

    /**
     * 查询播放次数前n的歌曲
     *
     * @param count
     * @return
     */
    @ApiOperation(value = "查询播放次数前n的歌曲")
    @GetMapping("order/{count}")
    public Result<List<Song>> songOrderByPlayCount(@PathVariable int count) {
        List<Song> songs = songService.songOrderByPlayCount(count);
        return Result.ok(songs);
    }

}
