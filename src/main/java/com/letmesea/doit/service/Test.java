package com.letmesea.doit.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Test {
    /**每期数据*/
    private static final String urlStr = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
    /**详情*/
    private static final String urlDetailStr = "https://kaijiang.500.com/shtml/ssq/21047.shtml";
    public static void main(String[] args) throws IOException {
        URL url = null;
        try {
            //https://www.zhcw.com/kjxx/ssq/kjxq/?kjData=2021044
            url = new URL(urlDetailStr);
            Document document = Jsoup.connect(urlDetailStr).ignoreContentType(true).timeout(30000).get();
//            String res = httpGetHeader(urlDetailStr,"","");
            if (document!=null&& document.body()!=null
                &&document.body().select("tr")!=null
                &&document.body().select("tr").select("td")!=null){
                //顺序
                String [] sq = document.body().select("tr").select("td").eachText().get(12).split(" ");
            }
            System.out.println(111);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static String httpGetHeader(String url,String cook,String header) throws IOException{
        //获取请求连接
        Connection con = Jsoup.connect(url).ignoreContentType(true);
        //请求头设置，特别是cookie设置
//        con.header("Accept", "text/html, application/xhtml+xml, */*");
//        con.header("Content-Type", "application/x-www-form-urlencoded");
//        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
//        con.header("Cookie", cook);
        //发送请求
        Connection.Response resp=con.method(Connection.Method.GET).execute();
        //获取cookie名称为__bsi的值
        String cookieValue = resp.cookie("__bsi");
        System.out.println("cookie  __bsi值：  "+cookieValue);
        //获取返回cookie所值
        Map<String,String> cookies = resp.cookies();
        System.out.println("所有cookie值：  "+cookies);
        //获取返回头文件值
        String headerValue = resp.header(header);
        System.out.println("头文件"+header+"的值："+headerValue);
        //获取所有头文件值
        Map<String,String> headersOne =resp.headers();
        System.out.println("所有头文件值："+headersOne);
        return headerValue;

    }
}
