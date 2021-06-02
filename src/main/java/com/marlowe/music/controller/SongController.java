package com.marlowe.music.controller;


import com.marlowe.music.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 歌曲表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongServiceImpl songService;

}
