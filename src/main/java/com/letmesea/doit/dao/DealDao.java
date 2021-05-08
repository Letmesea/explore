package com.letmesea.doit.dao;

import com.letmesea.doit.pojo.Kj;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzh
 */
@Repository
public interface DealDao {
    List<Kj> getData();
    Integer batchInsertSsq(List<Kj> kjs);
}
