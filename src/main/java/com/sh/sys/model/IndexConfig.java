package com.sh.sys.model;

import java.util.Date;

public class IndexConfig {
    private Integer configId;

    private String configName;

    private Byte configType;

    private Integer configTutorId;

    private Integer configSubId;

    private Integer configRank;

    private String redirectUrl;

    private Date createTime;

    private Integer createAdminId;

    private Date updateTime;

    private Integer updateAdminId;

    private Byte isExpire;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public Byte getConfigType() {
        return configType;
    }

    public void setConfigType(Byte configType) {
        this.configType = configType;
    }

    public Integer getConfigTutorId() {
        return configTutorId;
    }

    public void setConfigTutorId(Integer configTutorId) {
        this.configTutorId = configTutorId;
    }

    public Integer getConfigSubId() {
        return configSubId;
    }

    public void setConfigSubId(Integer configSubId) {
        this.configSubId = configSubId;
    }

    public Integer getConfigRank() {
        return configRank;
    }

    public void setConfigRank(Integer configRank) {
        this.configRank = configRank;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(Integer createAdminId) {
        this.createAdminId = createAdminId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateAdminId() {
        return updateAdminId;
    }

    public void setUpdateAdminId(Integer updateAdminId) {
        this.updateAdminId = updateAdminId;
    }

    public Byte getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(Byte isExpire) {
        this.isExpire = isExpire;
    }
}