package com.cl.crawler.service.strategy;

import com.cl.crawler.model.info.IpProxyInfo;

import java.util.List;

/**
 * by cl at 2020/5/19 0019
 */
public interface IpProxyService {

     void listIpProxyInfo(String url, List<IpProxyInfo> ipProxyInfoList);

}
