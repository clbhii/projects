package com.cl.crawler.service.strategy.impl;

import com.cl.common.util.http.HttpUtil;
import com.cl.crawler.model.info.IpProxyInfo;
import com.cl.crawler.service.strategy.abs.AbsIpProxyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * by cl at 2020/5/19 0019
 */
@Service("ipProxyXiciService")
public class IpProxyXiciServiceImpl extends AbsIpProxyService {

    @Override
    public void listIpProxyInfo(String url, List<IpProxyInfo> ipProxyInfoList) {
        HashMap<String, String> headMap = new HashMap<>();
        HashMap<String, String> dataMap = new HashMap<>();
        headMap.put("Host", "www.xicidaili.com");
        headMap.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        String html = HttpUtil.get(url, headMap, dataMap);
//        System.out.println(html);
        // 将html解析成DOM结构
        Document document = Jsoup.parse(html);
        // 提取所需要的数据
        Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");
        for (int i = 1; i < trs.size(); i++) {
            String ipAddress = trs.get(i).select("td").get(1).text();
            String ipPortStr = trs.get(i).select("td").get(2).text();
            Integer ipPort = Integer.parseInt(ipPortStr);
            String ipType = trs.get(i).select("td").get(5).text();
            String ipSpeedStr = trs.get(i).select("td").get(6).select("div[class=bar]").
                    attr("title");
            BigDecimal ipSpeed = new BigDecimal(ipSpeedStr.substring(0, ipSpeedStr.indexOf('秒')));
            if(ipSpeed.compareTo(new BigDecimal(3.0d)) > 0  ) {
                continue;
            }
            IpProxyInfo ipProxyInfo = new IpProxyInfo();
            ipProxyInfo.setIpAddress(ipAddress);
            ipProxyInfo.setIpPort(ipPort);
            ipProxyInfo.setIpType(ipType);
            ipProxyInfo.setIpSpeed(ipSpeed);
            ipProxyInfoList.add(ipProxyInfo);
        }
    }
}
