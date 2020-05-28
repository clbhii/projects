package com.cl.crawler.model;

import java.util.Date;

/**
 * 职位
 */
public class PositionDO {
    /**
     * uid
     */
    private String uid;

    /**
     * 外部id
     */
    private String outId;

    /**
     * 职位信息
     */
    private String title;

    /**
     * 职位详细url
     */
    private String detailUrl;

    /**
     * 发布时间
     */
    private Date datePublish;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 获取uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置uid
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * 获取外部id
     */
    public String getOutId() {
        return outId;
    }

    /**
     * 设置外部id
     */
    public void setOutId(String outId) {
        this.outId = outId == null ? null : outId.trim();
    }

    /**
     * 获取职位信息
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职位信息
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取职位详细url
     */
    public String getDetailUrl() {
        return detailUrl;
    }

    /**
     * 设置职位详细url
     */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    /**
     * 获取发布时间
     */
    public Date getDatePublish() {
        return datePublish;
    }

    /**
     * 设置发布时间
     */
    public void setDatePublish(Date datePublish) {
        this.datePublish = datePublish;
    }

    /**
     * 获取创建时间
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 设置创建时间
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}