package com.cl.crawler.model.info;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ip代理信息
 * by cl at 2020/5/12 0012
 */
public class IpProxyInfo implements Serializable {
    /**
     * ip 地址
     */
    private String ipAddress;
    /**
     * ip 端口
     */
    private Integer ipPort;
    /**
     * ip类型 HTTP 或是HTTPS
     */
    private String ipType;
    /**
     * ip速度
     */
    private BigDecimal ipSpeed;
    /**
     * // 使用计数器，连续三次这个IP不能使用，就将其从IP代理池中进行清除
     */
    private int userCount;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIpPort() {
        return ipPort;
    }

    public void setIpPort(Integer ipPort) {
        this.ipPort = ipPort;
    }

    public String getIpType() {
        return ipType;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    public BigDecimal getIpSpeed() {
        return ipSpeed;
    }

    public void setIpSpeed(BigDecimal ipSpeed) {
        this.ipSpeed = ipSpeed;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
