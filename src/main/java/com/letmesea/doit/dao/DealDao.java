package com.letmesea.doit.dao;

import com.letmesea.doit.dto.CityDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzh
 */
@Repository
public interface DealDao {
    List<CityDto> getData();
    Integer batchInsertSsq(List<CityDto> kjs);
}
