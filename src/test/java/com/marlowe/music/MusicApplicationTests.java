package com.marlowe.music;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MusicApplicationTests {

    @Test
    void contextLoads() throws IOException {
        List<Integer> allSingerId = new ArrayList<>();

        String url = "https://music.163.com/discover/artist/cat?id=1001";

        System.out.println(url);
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url).openStream(), "utf-8", url);

            Elements names = doc.select("#m-artist-box li a.s-fc0");

            for (Element element : names) {

                String id = element.attr("href").substring(11);
                String name = element.text();
                System.out.println(name);
                System.out.println(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    void test2() throws IOException {
        String url = "https://music.163.com/artist?id=3684";

        System.out.println(url);
        Document doc = null;
        try {
            String res = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                    .header("Referer", "https://music.163.com/")
                    .method(Connection.Method.GET)
                    .timeout(30000).execute().charset("utf-8").body(); // 设置请求头等信息，模拟人工访问，超时时间可自行设置
            doc = Jsoup.parse(res);
//            System.out.println(doc);

            // 解析获取歌曲对象
            Elements songElements = doc.select("ul.f-hide li");
            System.out.println(songElements);


            // 获得歌手图片
            String pic = doc.select("img").attr("src");
            System.out.println(pic);
//
//            System.out.println(doc.select("div.n-artdesc p"));
//
//            String introduction = doc.select("div.n-artdesc p").eq(0).text();
//            System.out.println(introduction);

//            for (Element element : songElements) {
//                String songId = element.getElementsByTag("a").attr("href").substring(9);
//                String title = element.text();
////                System.out.println(songId);
////                System.out.println(title);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3() {
        int[] type = {1001, 1002, 1003, 2001, 2002, 2003, 6001, 6002, 6003, 7001, 7002, 7003, 4001, 4002, 4003};
        int[] enName = {-1, 0, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
                80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
        for (int i = 0; i < type.length; i++) {

            for (int j = 0; j < enName.length; j++) {
                String url = "https://music.163.com/discover/artist/cat?id=" + type[i];
                System.out.println(url);

                Document doc = null;
                try {
                    String res = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                            .header("Accept-Encoding", "gzip, deflate, br")
                            .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                            .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                            .header("Referer", "https://music.163.com/discover/artist/cat?id=1001&initial=65")
                            .header("Upgrade-Insecure-Requests", "1")
                            .method(Connection.Method.GET)
                            .timeout(30000).response().charset("utf-8").body(); // 设置请求头等信息，模拟人工访问，超时时间可自行设置
                    doc = Jsoup.parse(res);
                    Elements names = doc.select("#m-artist-box li a.s-fc0");

                    for (Element element : names) {

                        String mess = "{\"name\":" + "\"" + element.text() + "\"," +
                                "\"uid\":" + "\"" + element.attr("href").
                                replace("/artist?id=", "").trim() + "\"}";
                        System.out.println(mess);
//                        FileUtils.saveConToFile(mess, "g://singer.json"); // 可自行写存储信息的代码
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }


}
