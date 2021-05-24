package com.marlowe.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: music
 * @description: test
 * @author: Marlowe
 * @create: 2021-05-24 17:31
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
