package com.marlowe.music.reptile;

import com.marlowe.music.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBoot-VUE-Music
 * @description: 爬虫控制器
 * @author: Marlowe
 * @create: 2021-05-31 10:08
 **/
@RestController
@RequestMapping("/reptile")
public  class ReptileController {

    @Autowired
    private ReptileService reptileService;

    @GetMapping("start")
    public Result startReptile() {

        return reptileService.parseSongs();
    }
}