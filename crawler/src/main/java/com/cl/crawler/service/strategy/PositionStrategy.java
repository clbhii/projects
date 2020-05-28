package com.cl.crawler.service.strategy;

import com.cl.crawler.model.PositionDO;

import java.util.List;

/**
 * by cl at 2020/5/19 0019
 */
public interface PositionStrategy {

    void crawl(String url, List<PositionDO> positionDOList);
}
