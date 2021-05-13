package com.letmesea.doit.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.letmesea.doit.dao.DealDao;
import com.letmesea.doit.dto.Ssq;
import com.letmesea.doit.dto.WinHistory;
import com.letmesea.doit.pojo.Kj;
import com.letmesea.doit.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealService {
    private static final Logger logger = Logger.getLogger(DealService.class);
    /**每期数据*/
    private static final String urlStr = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
    private static final String drt = "http://kaijiang.zhcw.com/zhcw/inc/ssq/ssq_wqhg.jsp?pageNum=110";
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
            for (int i=1;i<=1;i++){
                Document document = null;
                String url = "http://kaijiang.zhcw.com/zhcw/inc/ssq/ssq_wqhg.jsp?pageNum="+i;
                try{
                    document = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
                }catch (Exception e){
                    logger.error("第"+i+"页获取异常,正在重试...");
                    for (;;){
                        try {
                            document = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
                            if (document!=null)break;
                        }catch (Exception e1){
                            logger.error("第"+i+"页重试失败，继续重试...");
                        }
                    }
                }
                Elements elements = document.body().select("tr").select("td");
                List<String> every = elements.eachText();
                Kj kj = new Kj();
                for (int j=0;j<=every.size()-1;j++){
                    if (StringUtils.isEmpty(kj.getOpenDtm())){
                        kj.setOpenDtm(every.get(j));
                    } else if (StringUtils.isEmpty(kj.getNumber())){
                        String n = every.get(j).substring(2);
                        kj.setNumber(n);
                        Document documentd = null;
                        String urld = "https://kaijiang.500.com/shtml/ssq/"+n+".shtml";
                        try {
                            documentd = Jsoup.connect(urld).ignoreContentType(true).timeout(30000).get();
                        }catch (Exception e){
                            logger.error("第"+n+"期出球顺序获取异常，正在重试...");
                            for (;;){
                                try{
                                    documentd = Jsoup.connect(urld).ignoreContentType(true).timeout(30000).get();
                                    if (documentd!=null)break;
                                }catch (Exception e1){
                                    logger.error("第"+n+"期出球顺序重试失败，继续重试...");
                                }
                            }
                        }
                        List<String> bq = documentd.body().select("tr").select("td").eachText();
                        if (bq.contains("出球顺序：")){
                            int idx = bq.indexOf("出球顺序：")+1;
                            if(idx<bq.size()){
                                String sq = bq.get(12);
                                logger.info("第"+n+"期顺序："+sq);
                                kj.setSqNumber(sq);
                            }
                        }
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
                        break;
                    }
                }
            }
            System.out.println(111);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        try {
            return dealDao.batchInsertSsq(kjs);
        }catch (Exception e){
            logger.error("异常入参："+JSONObject.toJSONString(kjs));
            logger.error("插入异常,{}",e);
            return null;
        }
    }
    public Integer batchInsertFromFile(){
        String filePath = this.getClass().getResource("/").getPath()+"data/03001-21049-detail.json";
        String filePath1 = this.getClass().getResource("/").getPath()+"data/03001-21049-sq.json";
        String res = FileUtil.readFile(filePath);
        String res1 = FileUtil.readFile(filePath1);

        List<Ssq> ssqs = JSONArray.parseArray(res,Ssq.class);
        List<Ssq> ssqs1 = JSONArray.parseArray(res1,Ssq.class);
        List<Kj> kjs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ssqs))
        {
            for (Ssq ssq:ssqs){
                Kj kj = new Kj();
                kj.setNumber(ssq.getQi());
                kj.setOpenDtm(ssq.getOpenDate());
                kj.setFirstPrizeNum(ssq.getFstNum());
                kj.setSecondPrizeNum(ssq.getSecNum());
                String winNumber = ssq.getFst()+" "+ssq.getSec()+" "+
                        ssq.getThrd()+" "+ssq.getFour()+" "+ssq.getFive()+" "+ssq.getSix()+" "+ssq.getSeven();
                kj.setWinNumber(winNumber);
                kj.setTotalAmount(ssq.getTotalA());
                kjs.add(kj);
            }
        }
        if (!CollectionUtils.isEmpty(ssqs1)){
            for (Ssq ssq:ssqs1){
                for (Kj kj :kjs){
                    if (kj.getNumber().equals(ssq.getQi())){
                        String sq = ssq.getSqFst()+" "+ssq.getSqSec()+" "+
                                ssq.getSqThrd()+" "+ssq.getSqFour()+" "+
                                ssq.getSqFive()+" "+ssq.getSqSix();
                        kj.setSqNumber(sq);
                        break;
                    }
                }
            }
        }
        return dealDao.batchInsertSsq(kjs);
    }

}
