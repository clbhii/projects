package com.cl.crawler.task;

import com.cl.crawler.model.info.IpProxyInfo;
import com.cl.crawler.service.strategy.IpProxyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * by cl at 2020/5/12 0012
 */
public class IpProxyXiciTask extends TimerTask {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    @Qualifier("ipProxyXiciService")
    private IpProxyService ipProxyXiciService;
    /**
     * 免费代理ip网站
     */
    private String baseUrl = "http://www.xicidaili.com/nn/";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run() {
        List<IpProxyInfo> ipProxyInfoList = new ArrayList<>();
        for(int i = 1; i < 2; i++) {
            ipProxyXiciService.listIpProxyInfo(baseUrl + i, ipProxyInfoList);
        }
        try {
            System.out.println(objectMapper.writeValueAsString(ipProxyInfoList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        redisTemplate.
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new IpProxyXiciTask(), 1000);
    }

}
