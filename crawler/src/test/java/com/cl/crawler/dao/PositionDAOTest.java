package com.cl.crawler.dao;


import com.cl.common.util.uuid.UUIDUtil;
import com.cl.crawler.model.PositionDO;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * by cl at 2020/5/19 0019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionDAOTest {
    @Autowired
    private PositionDAO positionDAO;

    @Test
    public void insert() {
        PositionDO positionDO = new PositionDO();
        positionDO.setUid(UUIDUtil.randomUUID());
        positionDO.setOutId("test01");
        positionDO.setTitle("测试职位");
        positionDO.setDetailUrl("测试url");
        Date date = new Date();
        positionDO.setDatePublish(date);
        positionDO.setDateCreate(date);
        positionDAO.insert(positionDO);
    }


}
