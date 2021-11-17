package com.letmesea.doit.service;

import com.alibaba.fastjson.JSONObject;
import com.letmesea.doit.dao.DealDao;
import com.letmesea.doit.dto.City;
import com.letmesea.doit.dto.CityDto;
import com.letmesea.doit.dto.ProvinceData;
import com.letmesea.doit.dto.Res;
import com.letmesea.doit.utils.Combinations1ton;
import com.letmesea.doit.utils.FileUtil;
import com.letmesea.doit.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DealService {
    private static final Logger logger = Logger.getLogger(DealService.class);

    @Autowired
    private DealDao dealDao;
    @Autowired
    RedisUtils redisUtils;

    @PostConstruct
    void loadData(){
        Integer res = batchInsertFromFile();
        logger.info("加载..."+res);
    }
    //刷新
    public void refresh(){
        getAndRefreshData();
    }
    public List<ProvinceData> getProvinData(){
        if (redisUtils.get("provindata")!=null){
            return (List<ProvinceData>) redisUtils.get("provindata");
        }
//        redisUtils.setnxAndExpire("provindata",provinceData,60);
        List<CityDto> kjs = dealDao.getData();
        //按省份分组
        Map<String,List<CityDto>> stringListMap = kjs.stream().
                filter(it->!StringUtils.isEmpty(it.getAreaNm1())).
                collect(Collectors.groupingBy(CityDto::getAreaNm1));
        List<ProvinceData> provinceData = new ArrayList<>();
        for (Map.Entry<String,List<CityDto>> ssdr:stringListMap.entrySet()){
            ProvinceData provinceData1 = new ProvinceData();
            provinceData1.setName(ssdr.getKey());//广东
            List<City> cityList = new ArrayList<>();

            List<CityDto> ssdr1 = ssdr.getValue();
            //按照城市分组
            Map<String,List<CityDto>> stringListMap1 = ssdr1.stream().
                    filter(it->!StringUtils.isEmpty(it.getAreaNm2())).
                    collect(Collectors.groupingBy(CityDto::getAreaNm2));
            for (Map.Entry<String,List<CityDto>> ssdr2:stringListMap1.entrySet()){
                City city = new City();
                List<String> arealist = new ArrayList<>();
                city.setName(ssdr2.getKey());
                //区域
                List<CityDto> area = ssdr2.getValue();
                arealist = area.stream().map(it->it.getAreaNm3()).collect(Collectors.toList());
                city.setAreaList(arealist);
                cityList.add(city);
            }
            provinceData1.setCityList(cityList);
            provinceData.add(provinceData1);
        }
        //存入redis
        redisUtils.setnxAndExpire("provindata",provinceData,60);
        return provinceData;
    }


    /**
     *
     */
    public void getAndRefreshData() {
        List<CityDto> kjs = dealDao.getData();
        boolean res =  redisUtils.setnxAndExpire("cityData",kjs,60);
        logger.info("刷新成功");
    }




    public Integer batchInsertFromFile() {
        String filePath = this.getClass().getResource("/").getPath() + "城市数据.json";
        String res = FileUtil.readFile(filePath);
        Res res1 = JSONObject.parseObject(res,Res.class);
        List<CityDto> zuiz =  res1.getResult().getDtList().stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(CityDto::getAreaName))
                ), ArrayList::new)
        );
        //入库
        int cot = dealDao.batchInsertSsq(zuiz);
        //存入redis
        redisUtils.setnxAndExpire("cityData",zuiz,60);
        return cot;
    }


}
