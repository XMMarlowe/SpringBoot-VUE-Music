package com.marlowe.music.reptile;

import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.SingerMapper;
import com.marlowe.music.mapper.SongMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @program: SpringBoot-VUE-Music
 * @description:
 * @author: Marlowe
 * @create: 2021-05-31 10:11
 **/
@Service
public class ReptileUtils {

    /**
     * 先爬取所有的歌手id
     * 根据歌手id，爬取一个歌手的所有歌曲就插入数据库
     */

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    SongMapper songMapper;

    /**
     * 获得所有的歌曲
     *
     * @param pageId
     * @return
     */
    public boolean getAllSongs(int pageId) {
        int cnt = 0;

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

        System.out.println("======================================================开始爬虫============================");


        String singerUrl = "https://music.163.com/discover/artist/cat?id=" + pageId;

        System.out.println("歌手页面url:" + singerUrl);

        // 获得歌手列表页面源代码，解析所有歌手id
        Document doc1 = null;
        try {
            String res1 = Jsoup.connect(singerUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                    .header("Referer", "https://music.163.com/")
                    .method(Connection.Method.GET)
                    // 设置请求头等信息，模拟人工访问，超时时间可自行设置
                    .timeout(30000).execute().charset("utf-8").body();
            doc1 = Jsoup.parse(res1);

            Elements elements = doc1.select("#m-artist-box li a.s-fc0");

            // 获取所有的歌手id
            for (Element element : elements) {
                System.out.println("---------------------开始爬取当前歌手信息--------------------");

                // 解析元素获取歌手id
                String singerId = element.attr("href").replaceAll("[^0-9]", "");

                // 解析元素获取歌手姓名
                String singerName = element.text();
                System.out.println("歌手姓名:" + singerName);
                System.out.println("歌手id:" + singerId);

                // 通过歌手id获取歌曲和图片
                String songUrl = "https://music.163.com/artist?id=" + singerId;

                System.out.println("歌曲页面url:" + songUrl);

                // 通过歌手id获取简介
                String singerDescUrl = "https://music.163.com/artist/desc?id=" + singerId;
                System.out.println("歌手简介页面url:" + singerDescUrl);

                // 获得歌手页面源代码，解析歌手的所有歌曲id
                Document doc2 = null;
                // 获得歌手页面源代码，解析歌手的简介
                Document doc3 = null;

                try {
                    String res2 = Jsoup.connect(songUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                            .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                            .header("Referer", "https://music.163.com/")
                            .method(Connection.Method.GET)
                            // 设置请求头等信息，模拟人工访问，超时时间可自行设置
                            .timeout(30000).execute().charset("utf-8").body();
                    doc2 = Jsoup.parse(res2);

                    String res3 = Jsoup.connect(singerDescUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                            .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                            .header("Referer", "https://music.163.com/")
                            .method(Connection.Method.GET)
                            // 设置请求头等信息，模拟人工访问，超时时间可自行设置
                            .timeout(30000).execute().charset("utf-8").body();
                    doc3 = Jsoup.parse(res3);

                    // 解析获取歌曲对象
                    Elements songElements = doc2.select("ul.f-hide li");
//                    System.out.println("----------------------------");
//                    System.out.println(songElements);
//                    System.out.println("----------------------------");


                    // 进入歌手页面获得歌手的图片
                    String pic = doc2.select("div.n-artist img").attr("src");
                    System.out.println("歌手图片url:" + pic);

                    // 进入歌手页面获得歌手的简介
                    String introduction = doc3.select("div.n-artdesc p").eq(0).text();
                    System.out.println("歌手desc:" + introduction);


                    System.out.println("=======当前歌手所有音乐=======");

                    String singerLocationAndGender = map.get(pageId);
                    String location = singerLocationAndGender.substring(0, 2);
                    String gender = singerLocationAndGender.substring(2, 3);
                    int sex = 0;
                    if ("男".equals(gender)) {
                        sex = 1;
                    } else if ("女".equals(gender)) {
                        sex = 0;
                    } else {
                        sex = 2;
                    }
                    Singer singer = new Singer()
                            .setSingerId(singerId)
                            .setName(singerName)
                            .setSex(sex)
                            .setPic(pic)
                            .setBirth(null)
                            .setLocation(location)
                            .setIntroduction(introduction);

                    // 将歌手信息插入数据库
//                    int insert = singerMapper.insert(singer);
//                    System.out.println(insert);

                    // 获取当前歌手下所有热门歌曲
                    for (Element el : songElements) {
                        String songId = el.getElementsByTag("a").attr("href").replaceAll("[^0-9]", "");
                        String title = el.text();
                        System.out.println("歌曲id：" + songId);
                        System.out.println("歌曲名：" + title);

                        // 将数据封装，插入数据库
                        Song song = new Song()
                                .setSongId(songId)
                                .setSingerId(singerId)
                                .setName(title)
                                .setIsDownload(0);
                        // 将歌曲信息插入数据库
//                        int insert1 = songMapper.insert(song);
//                        System.out.println(insert1);
                        cnt++;

                    }
                    System.out.println("当前歌手歌曲数量共：" + songElements.size());
                    System.out.println("=======当前歌手所有音乐获取完毕=======");
                    System.out.println("---------------------当前歌手所有音乐获取结束--------------------");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("共爬取：" + cnt);
        System.out.println("========================爬取音乐结束========================");
        return true;
    }


}