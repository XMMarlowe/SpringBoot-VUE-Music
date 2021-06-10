package com.marlowe.music.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.service.ISingerService;
import com.marlowe.music.service.impl.SingerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "歌手管理控制类")
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
    @ApiOperation(value = "添加歌手")
    @PostMapping("add")
    public Result addSinger(@RequestBody Singer singer) {
        boolean addSinger = singerService.addSinger(singer);
        if (addSinger) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除歌手
     *
     * @return
     */
    @ApiOperation(value = "根据id删除歌手")
    @GetMapping("delete/{id}")
    public Result deleteSinger(@PathVariable String id) {
        boolean deleteSinger = singerService.deleteSinger(id);
        if (deleteSinger) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 批量删除歌手
     *
     * @param idList
     * @return
     */
    @ApiOperation("批量删除歌手")
    @PostMapping("deletes")
    public Result deleteSingers(@RequestBody List<Integer> idList) {
        boolean deleteSingers = singerService.deleteSingers(idList);
        if (deleteSingers) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 通过主键id更新歌手信息
     *
     * @return
     */
    @ApiOperation(value = "通过主键id更新歌手信息")
    @PostMapping("update")
    public Result updateSingerMsg(@RequestBody Singer singer) {
        boolean update = singerService.updateSingerMsg(singer);
        if (update) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }


    /**
     * 根据姓名查找歌手
     *
     * @return
     */
    @ApiOperation(value = "根据姓名查找歌手")
    @GetMapping("detail-name/{name}/{pageNo}/{pageSize}")
    public Result<List<Singer>> findSingerByName(@PathVariable("name") String name, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Singer> pageInfo = singerService.findSingerByName(name, pageNo, pageSize);
        List<Singer> singers = pageInfo.getList();
        return Result.ok(singers);
    }

    /**
     * 根据性别查找歌手
     *
     * @return
     */
    @ApiOperation(value = "根据性别查找歌手")
    @GetMapping("detail-sex/{sex}/{pageNo}/{pageSize}")
    public Result<List<Singer>> findSingerBySex(@PathVariable("sex") int sex, @PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Singer> pageInfo = singerService.findSingerBySex(sex, pageNo, pageSize);
        List<Singer> singers = pageInfo.getList();
        return Result.ok(singers);
    }


    /**
     * 查询所有歌手
     *
     * @return
     */
    @ApiOperation(value = "查询所有歌手")
    @GetMapping("allSinger/{pageNo}/{pageSize}")
    public Result<List<Singer>> allSinger(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Singer> pageInfo = singerService.allSinger(pageNo, pageSize);
        List<Singer> singers = pageInfo.getList();
        return Result.ok(singers);
    }

    /**
     * 获得歌手总数
     *
     * @return
     */
    @ApiOperation(value = "获得歌手总数")
    @GetMapping("count")
    public Result singerCount() {
        int singerCount = singerService.singerCount();
        return Result.ok(singerCount);
    }

    /**
     * 根据歌手性别统计
     *
     * @return
     */
    @ApiOperation(value = "根据歌手性别统计")
    @GetMapping("detail/sex")
    public Result singerCountOfSex() {
        JSONObject jsonObject = new JSONObject();
        // 查询出男歌手个数
        int countOfSex1 = singerService.singerCountOfSex(1);
        jsonObject.put("男", countOfSex1);
        // 查询出女歌手个数
        int countOfSex0 = singerService.singerCountOfSex(0);
        jsonObject.put("女", countOfSex0);
        // 查询出组合歌手个数
        int countOfSex2 = singerService.singerCountOfSex(2);
        jsonObject.put("组合", countOfSex2);
        return Result.ok(jsonObject);
    }
}
