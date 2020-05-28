package com.cl.crawler.dao;


import com.cl.crawler.model.PositionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PositionDAO {
    int insert(PositionDO record);

    PositionDO selectByPrimaryKey(String uid);

    PositionDO selectByOutId(String outId);
}