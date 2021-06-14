package com.marlowe.music;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@SpringBootTest
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
//
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
        String songPicUrl = "http://music.163.com/api/song/detail/?id=" + 326904 + "&ids=%5B" + 326904 + "%5D";

        System.out.println(songPicUrl);

        Document doc5 = null;
        String songPic = "";

        String albumName = "";

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

                    if (album.has("name")) {
                        JsonElement name = album.get("name");
                        if (!name.isJsonNull()) {
                            albumName = name.getAsString();
                        }
                    }

                    if (album.has("picUrl")) {
                        JsonElement picUrl = album.get("picUrl");
                        if (!picUrl.isJsonNull()) {
                            songPic = picUrl.getAsString();
                        }
                    }

                }
            }

            System.out.println(albumName);
            System.out.println(songPic);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSongPic1AndAlbum() {
        String songPicUrl = "https://music.163.com/song?id=326904";

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

//            String attr = doc.select(".u-cover-6").select("img").attr("data-src");

//            System.out.println(attr);

//            String albumName = doc.select("p.des").eq(1).select("a").text();

//            System.out.println(albumName);
            Elements select = doc.select("span.time");
            System.out.println(select);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getSongTime() {
        // 通过歌曲id，获得歌曲歌词
        String songTimeUrl = "http://music.163.com/api/song/detail/?id=1840187827&ids=%5B1840187827%5D";

        Document doc4 = null;
        String lyric = "";
        try {
            String res4 = Jsoup.connect(songTimeUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
                    .header("Content-Type", "Application/json")
                    .header("Cookie", "_ntes_nnid=d6153dc520bfcc9921f18445924ffafd,1591659800916; _ntes_nuid=d6153dc520bfcc9921f18445924ffafd; vinfo_n_f_l_n3=3bc86f710ea3bc7e.1.0.1608688125069.0.1608688127907; nts_mail_user=15023420348@163.com:-1:1; NTES_SESS=ZoExt2ss9_.Cq6_lx5ne4YjynX7tE8WUUMWidWatTFehj0mKjiCG5_bpNb9YTy4uL.nP3JdWQDk8xdD2NQBSQk2ombtgKPK61ZC13L6A9eTkhBx.vbdeMuTonvx_j.rv8DDnufIwFpUAO4dYEBCmMLfgaO6xOTraIs3nqWcmxLEPJCIy.Ux927t4J9CTKAHKJMERTs0iWwlP_YRqi28wWYemLuAG1c42C; S_INFO=1619313161|0|3&80##|m15023420348; P_INFO=m15023420348@163.com|1619313161|0|oahz|00&99|chq&1617953597&oahz#chq&null#10#0#0|150348&1|oahz|15023420348@163.com; _iuqxldmzr_=32; NMTID=00Ohyi03Yw_4VVYkEtjj5BOENsyaD4AAAF5f_ws0g; WEVNSM=1.0.0; WM_TID=3iM7baj1EFRBRVQARQZ%2FwoxShNDRV95E; playerid=25988013; ntes_kaola_ad=1; MUSIC_U=bb3690f994cc43e13ac7b4c6350e9fafeb2de0697d8305ad88c80e2a9cf328ac0931c3a9fbfe3df2; __csrf=7bbadbcb947ddfd73e2aeeb2e0f8e215; csrfToken=VoxKYe95Q-8MnBq3UCgbHQU8; WM_NI=ekB5ZbzfWsq5s7EDmF64Fl7Wt7XUNEabE8YsOTdWaxiMj7g5CSfyQr1gVA486p79RT2rV%2Fg9b7XTRfGoifHTxL7ppTj6SaF2dJAxCQL5vT0LM%2F5wriQIKRo%2Fb0Hu0atzYjY%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee82db6bba91a78eb77aba928eb6c54b879e9bbbf161b69897a8cd4282b58bb0ae2af0fea7c3b92aa7b58ed6d96ababa9994f43fb1e98ba5f04ef6ec8a8db65db8e998b6cd4fa3be8bd9dc798d8cacb8e474ab93a6d8e63f818daa9ad47af3b99aa2b734f6b0f9dad160f2ae9ad0e633a2e998b5d16e85b1a787cb60bc94f991f360aaea9d82c53aa8968f99c25b90ebbab5b3488ff1fb98f163939785b3ee25a2f08896b36bb8a6978bdc37e2a3; JSESSIONID-WYYY=8a4oPlx%2FIoX2316j9hqSU4cy%2BUMKtW1SmEXJTz50j1q%2BGxrU%2Fa063dy4s%5CasrXI1oaPHmwP%2F8Ac%5CTTzDoZai%2BVihpgx04BK3t9kvXYsg3yX6UBi9D%2FH3S0yBRbWHe5GJ0Pia5sipr5WKy0ai3nHBaqmK%5C7YDTf%2B5xmPK1%2BJXJB04HTqy%3A1622636619775; WNMCID=qnvdef.1622635770913.01.0")
                    .header("Referer", "https://music.163.com/")
                    .header("Host", "music.163.com")
                    .method(Connection.Method.GET)
                    // 设置请求头等信息，模拟人工访问，超时时间可自行设置
                    .timeout(30000).execute().charset("utf-8").body();

            doc4 = Jsoup.parse(res4);
            System.out.println(doc4);

            String lyricObject = doc4.body().text();

            JsonParser jp = new JsonParser();

            JsonObject jsonObject = jp.parse(lyricObject).getAsJsonObject();

            System.out.println(jsonObject);

//            if (jsonObject.has("lrc")) {
//                JsonObject lrc = jsonObject.get("lrc").getAsJsonObject();
//                if (lrc.has("lyric")) {
//                    JsonElement lyric1 = lrc.get("lyric");
//                    if (!lyric1.isJsonNull()) {
//                        lyric = lyric1.getAsString();
//                    }
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void TestSongTime() throws URISyntaxException, IOException {
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
//            Elements songElements = doc.select("ul.f-hide li");
//            System.out.println(songElements);

            String songTime = doc.select("#song-list-pre-data").text();
//            System.out.println(text);

            List<String> songTimes = new ArrayList();
            JSONArray jarr = JSONArray.parseArray(songTime);//JSON.parseArray(jsonStr);
            for (Iterator iterator = jarr.iterator(); iterator.hasNext(); ) {
                JSONObject job = (JSONObject) iterator.next();
                String weibo = job.get("duration").toString();
//                System.out.println(weibo);
                songTimes.add(weibo);
            }

            System.out.println(songTimes);


//
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


