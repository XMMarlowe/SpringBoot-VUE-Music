package com.marlowe.music.controller;


import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.service.ISingerService;
import com.marlowe.music.service.impl.SingerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * 歌手表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\IDE_Project\\JavaLearning\\music-website\\music-server\\img\\singerPic");
        }
    }

    /**
     * 添加歌手
     *
     * @return
     */
    @PostMapping("add")
    public Result<Singer> addSinger(@RequestBody Singer singer) {
        singerService.addSinger(singer);
        return Result.ok("添加成功");
    }

    /**
     * 删除歌手
     *
     * @return
     */
    @GetMapping("delete/{id}")
    public Result deleteSinger(@PathVariable String id) {
        int deleteSinger = singerService.deleteSinger(id);
        return Result.ok("删除成功");
    }

    /**
     * 更新歌手信息
     *
     * @return
     */
    @PostMapping("update")
    public Result updateSingerMsg(@RequestBody Singer singer) {
        int update = singerService.updateSingerMsg(singer);
        if (update > 0) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 更新歌手头像
     *
     * @return
     */
    @PostMapping("avatar/update")
    public Result updateSingerPic(@RequestParam("file") MultipartFile avatarFile,
                                  @RequestParam("id") int id) {

        return null;
    }


    /**
     * 根据姓名查找歌手
     *
     * @return
     */
    @GetMapping("detail-name/{name}")
    public Result<List<Singer>> findSingerByName(@PathVariable("name") String name) {
        List<Singer> singers = singerService.findSingerByName(name);
        return Result.ok(singers);
    }

    /**
     * 根据性别查找歌手
     *
     * @return
     */
    @GetMapping("detail-sex/{sex}")
    public Result<List<Singer>> findSingerBySex(@PathVariable("sex") int sex) {
        List<Singer> singers = singerService.findSingerBySex(sex);
        return Result.ok(singers);
    }


    /**
     * 查询所有歌手
     *
     * @return
     */
    @GetMapping("allSinger")
    public Result<List<Singer>> allSinger() {
        List<Singer> singers = singerService.allSinger();
        System.out.println(singers);
        Result result = Result.ok(singers);
        System.out.println(result);
        return Result.ok(singers);
    }


}
