package com.letmesea.doit.dao;

import com.letmesea.doit.pojo.Kj;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lzh
 */
@Repository
public interface DealDao {
    List<String> getData();
    List<String> getKjData(Integer qi);
    List<String> getSsqAllData();
    Integer batchInsertSsq(List<Kj> kjs);
    Integer batchInsertSsqAll(LinkedList<String> req);
}
