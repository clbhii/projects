package com.cl.crawler.service.strategy.impl;


import com.cl.common.util.date.DateUtil;
import com.cl.common.util.http.HttpUtil;
import com.cl.common.util.uuid.UUIDUtil;
import com.cl.crawler.dao.PositionDAO;
import com.cl.crawler.model.PositionDO;
import com.cl.crawler.service.strategy.abs.AbsPositionStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by cl at 2020/5/19 0019
 */
@Component("positionStrategyEleduck")
public class PositionStrategyEleduck extends AbsPositionStrategy {

    private static final String DETAIL_BASE_URL = "https://eleduck.com/posts/";
    @Autowired
    private PositionDAO positionDAO;

    @Override
    public void crawl(String url, List<PositionDO> positionDOList) {
        Map<String, String> headMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        String html = HttpUtil.get(url, headMap, paramMap);
        System.out.println(html);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try {
             map = objectMapper.readValue(html, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("解析数据失败", e);
        }
        List<Map<String, Object>> list = (List)map.get("posts");
        for(Map<String, Object> item : list) {
            String outId = (String)item.get("id");
            String title = (String)item.get("title");
            String publishAt = (String)item.get("published_at");
            PositionDO positionDO = positionDAO.selectByOutId(outId);
            if(positionDO != null) {
                continue;
            }else {
                positionDO = new PositionDO();
            }
            positionDO.setUid(UUIDUtil.randomUUID());
            positionDO.setOutId(outId);
            positionDO.setTitle(title);
            positionDO.setDetailUrl(DETAIL_BASE_URL + outId);
            Date date = DateUtil.parse(publishAt.split("T")[0], DateUtil.DEFAULT_DATE);
            positionDO.setDatePublish(date);
            positionDAO.insert(positionDO);
        }
    }
}
