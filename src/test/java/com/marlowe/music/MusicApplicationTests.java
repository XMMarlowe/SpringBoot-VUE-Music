package com.marlowe.music;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    void getLyric() {

        String lyricUrl = "https://music.163.com/api/song/lyric?id=" + 1412264550 + "&lv=1&kv=1&tv=-1";

        System.out.println(lyricUrl);
        Document doc = null;
        try {
            String res = Jsoup.connect(lyricUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                    .header("Referer", "https://music.163.com/")
                    .method(Connection.Method.GET)
                    .timeout(30000).execute().charset("utf-8").body(); // 设置请求头等信息，模拟人工访问，超时时间可自行设置
            doc = Jsoup.parse(res);


            String lyricObject = doc.body().text();
            System.out.println(lyricObject);
            JsonParser jp = new JsonParser();

            JsonObject jsonObject = jp.parse(lyricObject).getAsJsonObject();

            String lyric = jsonObject.get("lrc").getAsJsonObject().get("lyric").getAsString();
            System.out.println(lyric);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSongPic() {
        // 歌曲图片url
        String songPicUrl = "http://music.163.com/api/song/detail/?id=" + 1840187827 + "&ids=%5B" + 1840187827 + "%5D";

        System.out.println(songPicUrl);

        Document doc5 = null;
        String songPic = "";

        try {

            doc5 = Jsoup.parse(new URL(songPicUrl), 30000);

            String songPicObject = doc5.body().text();

            JsonParser jp = new JsonParser();

            JsonObject jsonObject = jp.parse(songPicObject).getAsJsonObject();
            System.out.println(jsonObject);
            if (jsonObject.has("songs")) {
                JsonArray jsonElement = (JsonArray) jsonObject.get("songs");
                JsonObject songs = (JsonObject) jsonElement.get(0);
                if (songs.has("album")) {
                    JsonObject album = songs.get("album").getAsJsonObject();
                    if (album.has("picUrl")) {
                        JsonElement picUrl = album.get("picUrl");
                        if (!picUrl.isJsonNull()) {
                            songPic = picUrl.getAsString();
                        }
                    }
                }
            }

            // 如果在上面没有找到歌曲url，下面再来试试
            if (songPic.length() == 0) {


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSongPic1() {
        String songPicUrl = "https://music.163.com/song?id=1321392748";

        System.out.println(songPicUrl);
        Document doc = null;
        try {
            String res = Jsoup.connect(songPicUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
                    .header("Referer", "https://music.163.com/")
                    .method(Connection.Method.GET)
                    .timeout(30000).execute().charset("utf-8").body(); // 设置请求头等信息，模拟人工访问，超时时间可自行设置
            doc = Jsoup.parse(res);
//            System.out.println(doc);

            String attr = doc.select(".u-cover-6").select("img").attr("data-src");
            System.out.println(attr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


