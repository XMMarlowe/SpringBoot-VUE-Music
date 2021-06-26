package com.marlowe.music.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.Song;
import com.marlowe.music.service.ISingerService;
import com.marlowe.music.service.impl.SingerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
@Slf4j
public class SingerController {

    @Autowired
    private ISingerService singerService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\IDE_Project\\JavaLearning\\music-website\\music-server\\img\\singerPic");
        }
    }

    /**
     * 根据歌手id获得歌手信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据歌手id获得歌手信息")
    @GetMapping("{id}")
    public Result findBySingerId(@PathVariable int id) {
        Singer singer = singerService.findBySingerId(id);
        return Result.ok(singer);
    }


    /**
     * 添加歌手
     *
     * @return
     */
    @ApiOperation(value = "添加歌手")
    @PostMapping("add")
    @RequiresRoles("root")
    public Result addSinger(@RequestBody Singer singer) {
        log.info("singer:" + singer);
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
    @RequiresRoles("root")
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
    @RequiresRoles("root")
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
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
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
     * 搜索框根据歌手名字模糊搜索
     *
     * @param keyWord
     * @return
     */
    @ApiOperation(value = "搜索框根据歌手名字模糊搜索")
    @PostMapping("search/{keyWord}")
    public Result<List<Singer>> searchSuggestion(@PathVariable String keyWord) {
        List<Singer> byNameLike = singerService.findByNameLike(keyWord);
        return Result.ok(byNameLike);
    }


    /**
     * 随机获得n个歌手
     *
     * @param num
     * @return
     */
    @ApiOperation(value = "随机获得n个歌手")
    @GetMapping("getRandomSinger/{num}")
    public Result<List<Singer>> getRandomSinger(@PathVariable int num) {
        List<Singer> res = new ArrayList();

        PageInfo<Singer> pageInfo = singerService.allSinger(1, 50);
        List<Singer> list = pageInfo.getList();

        // 获得歌单列表大小，生成索引随机数
        int size = list.size();

        if (size < num) {
            return Result.ok("当前歌手数不足，请调小请求个数");
        }

        // 获得不重复随机数索引
        List<Integer> songListIdList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            int nextInt = new Random().nextInt(size);
            if (!songListIdList.contains(nextInt)) {
                songListIdList.add(nextInt);
            } else {
                i--;
            }
        }

        for (int i = 0; i < songListIdList.size(); i++) {
            res.add(list.get(songListIdList.get(i)));
        }

        return Result.ok(res);
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

    /**
     * 根据歌手地区获得歌手数量
     *
     * @return
     */
    @ApiOperation(value = "根据歌手地区获得歌手数量")
    @GetMapping("detail/location")
    public Result singerCountOfLocation() {
        JSONObject jsonObject = new JSONObject();

        // 查询出华语歌手个数
        int countOfLocation1 = singerService.singerCountOfLocation("华语");
        jsonObject.put("华语", countOfLocation1);
        // 查询出欧美歌手个数
        int countOfLocation2 = singerService.singerCountOfLocation("欧美");
        jsonObject.put("欧美", countOfLocation2);
        // 查询出日本歌手个数
        int countOfLocation3 = singerService.singerCountOfLocation("日本");
        jsonObject.put("日本", countOfLocation3);
        // 查询出韩国歌手个数
        int countOfLocation4 = singerService.singerCountOfLocation("韩国");
        jsonObject.put("韩国", countOfLocation4);
        // 查询出其他歌手个数
        int countOfLocation5 = singerService.singerCountOfLocation("其他");
        jsonObject.put("其他", countOfLocation5);
        return Result.ok(jsonObject);
    }
}
