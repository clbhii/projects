package com.cl.crawler.service.strategy;

import com.cl.crawler.model.PositionDO;
import com.cl.crawler.service.strategy.PositionStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * by cl at 2020/5/19 0019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionStrategyEleduckTest {
    @Autowired
    @Qualifier("positionStrategyEleduck")
    private PositionStrategy positionStrategy;

    @Test
    public void crawl(){
        List<PositionDO> list = new ArrayList<>();
        positionStrategy.crawl("https://svc.eleduck.com/api/v1/posts?category=5&page=1", list);
    }
}
