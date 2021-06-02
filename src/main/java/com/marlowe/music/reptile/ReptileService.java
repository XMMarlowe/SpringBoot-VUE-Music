package com.marlowe.music.reptile;

import com.marlowe.music.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringBoot-VUE-Music
 * @description: 爬虫服务类
 * @author: Marlowe
 * @create: 2021-05-31 10:14
 **/
@Service
public class ReptileService {

    @Autowired
    private ReptileUtils reptileUtils;

    /**
     * 爬取歌曲存入数据库
     *
     * @return
     */
    public Result parseSongs() {

        /**
         * 存储歌手分类和pageID的对应信息
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1001, "华语男歌手");
        map.put(1002, "华语女歌手");
        map.put(1003, "华语组合/乐队");

        map.put(2001, "欧美男歌手");
        map.put(2002, "欧美女歌手");
        map.put(2003, "欧美组合/乐队");

        map.put(6001, "日本男歌手");
        map.put(6002, "日本女歌手");
        map.put(6003, "日本组合/乐队");

        map.put(7001, "韩国男歌手");
        map.put(7002, "韩国女歌手");
        map.put(7003, "韩国组合/乐队");

        map.put(4001, "其他男歌手");
        map.put(4002, "其他女歌手");
        map.put(4003, "其他组合/乐队");

        // 通过 pageId 获得页面下的所有歌手id，再根据歌手id爬取歌手页面下的所有歌曲
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            reptileUtils.getAllSongs(entry.getKey());
        }

        return Result.ok("爬取数据成功");
    }
}
