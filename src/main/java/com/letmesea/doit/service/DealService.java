package com.letmesea.doit.service;

import com.letmesea.doit.dao.DealDao;
import com.letmesea.doit.pojo.Kj;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {
    private static final Logger logger = Logger.getLogger(DealService.class);
    /**每期数据*/
    private static final String urlStr = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
    /**详情*/
    private static final String urlDetailStr = "https://kaijiang.500.com/shtml/ssq/21047.shtml";
    @Autowired
    private DealDao dealDao;
    public List<Kj> getData(){
        return dealDao.getData();
    }
    public Integer batchInsert(){
        List<Kj> kjs = new ArrayList<>();
        try {
            for (int i=1;i<=135;i++){
                String url = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_"+i+".html";
                Document document = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
                Elements elements = document.body().select("tr").select("td");
                List<String> every = elements.eachText();
                Kj kj = new Kj();
                for (int j=0;j<=every.size()-1;j++){
                    if (StringUtils.isEmpty(kj.getOpenDtm())){
                        kj.setOpenDtm(every.get(j));
                    } else if (StringUtils.isEmpty(kj.getNumber())){
                        String n = every.get(j).substring(2);
                        kj.setNumber(n);
                        String urld = "https://kaijiang.500.com/shtml/ssq/"+n+".shtml";
                        Document documentd = Jsoup.connect(urld).ignoreContentType(true).timeout(30000).get();
                        String sq = documentd.body().select("tr").select("td").eachText().get(12);
                        kj.setSqNumber(sq);
                    }else if (StringUtils.isEmpty(kj.getWinNumber())){
                        kj.setWinNumber(every.get(j));
                    }else if (StringUtils.isEmpty(kj.getTotalAmount())){
                        kj.setTotalAmount(every.get(j));
                    }else if (StringUtils.isEmpty(kj.getFirstPrizeNum())){
                        kj.setFirstPrizeNum(every.get(j));
                    }else if (StringUtils.isEmpty(kj.getSecondPrizeNum())){
                        kj.setSecondPrizeNum(every.get(j));
                    }
                    if (j%6==5){
                        kjs.add(kj);
                        kj=new Kj();
                    }
                }

//                if (document!=null&& document.body()!=null
//                        &&document.body().select("tr")!=null
//                        &&document.body().select("tr").select("td")!=null){
//                    //顺序
//                    String [] sq = document.body().select("tr").select("td").eachText().get(12).split(" ");
//                }
            }
            System.out.println(111);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return dealDao.batchInsertSsq(kjs);
    }

}
