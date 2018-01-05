package com.example.jason.test.GetSever;

import java.io.Serializable;

/**
 * Created by Jason on 2017/8/29.
 */

public class ServerList implements Serializable {

    //24個
    private String sid;
    private String site;
    private String name;
    private String content;
    private String checkStatus;
    private String isNameShow;
    private String isExpired;
    private String isExpiredReadable;
    private String isExpiredWritable;
    private String portable;
    private String portableIphone;
    private String portableIpad;
    private String portableAndroid;
    private String portableWindows;
    private String isSupportedBrief;
    private String isSupportedNews;
    private String isSupportedFAQ;
    private String isSupportedBannerAD;
    private String isSupportedOpenIDMod;
    private String isSupportedShoppingMod;
    private String isSupportedEPF;
    private String epfName;
    private String defaultLanguage;
    private String languageList;

    public ServerList(String sid, String site, String name, String content, String checkStatus,
                      String isNameShow, String isExpired, String isExpiredReadable, String isExpiredWritable,
                      String portable, String portableIphone, String portableIpad, String portableAndroid,
                      String portableWindows, String isSupportedBrief, String isSupportedNews,
                      String isSupportedFAQ, String isSupportedBannerAD, String isSupportedOpenIDMod,
                      String isSupportedShoppingMod, String isSupportedEPF, String epfName,
                      String defaultLanguage, String languageList) {

        this.sid = sid;
        this.site = site;
        this.name = name;
        this.content = content;
        this.checkStatus = checkStatus;
        this.isNameShow = isNameShow;
        this.isExpired = isExpired;
        this.isExpiredReadable = isExpiredReadable;
        this.isExpiredWritable = isExpiredWritable;
        this.portable = portable;
        this.portableIphone = portableIphone;
        this.portableIpad = portableIpad;
        this.portableAndroid = portableAndroid;
        this.portableWindows = portableWindows;
        this.isSupportedBrief = isSupportedBrief;
        this.isSupportedNews = isSupportedNews;
        this.isSupportedFAQ = isSupportedFAQ;
        this.isSupportedBannerAD = isSupportedBannerAD;
        this.isSupportedOpenIDMod = isSupportedOpenIDMod;
        this.isSupportedShoppingMod = isSupportedShoppingMod;
        this.isSupportedEPF = isSupportedEPF;
        this.epfName = epfName;
        this.defaultLanguage = defaultLanguage;
        this.languageList = languageList;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsNameShow() {
        return isNameShow;
    }

    public void setIsNameShow(String isNameShow) {
        this.isNameShow = isNameShow;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(String isExpired) {
        this.isExpired = isExpired;
    }

    public String getIsExpiredReadable() {
        return isExpiredReadable;
    }

    public void setIsExpiredReadable(String isExpiredReadable) {
        this.isExpiredReadable = isExpiredReadable;
    }

    public String getIsExpiredWritable() {
        return isExpiredWritable;
    }

    public void setIsExpiredWritable(String isExpiredWritable) {
        this.isExpiredWritable = isExpiredWritable;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getPortableIphone() {
        return portableIphone;
    }

    public void setPortableIphone(String portableIphone) {
        this.portableIphone = portableIphone;
    }

    public String getPortableIpad() {
        return portableIpad;
    }

    public void setPortableIpad(String portableIpad) {
        this.portableIpad = portableIpad;
    }

    public String getPortableAndroid() {
        return portableAndroid;
    }

    public void setPortableAndroid(String portableAndroid) {
        this.portableAndroid = portableAndroid;
    }

    public String getPortableWindows() {
        return portableWindows;
    }

    public void setPortableWindows(String portableWindows) {
        this.portableWindows = portableWindows;
    }

    public String getIsSupportedBrief() {
        return isSupportedBrief;
    }

    public void setIsSupportedBrief(String isSupportedBrief) {
        this.isSupportedBrief = isSupportedBrief;
    }

    public String getIsSupportedNews() {
        return isSupportedNews;
    }

    public void setIsSupportedNews(String isSupportedNews) {
        this.isSupportedNews = isSupportedNews;
    }

    public String getIsSupportedFAQ() {
        return isSupportedFAQ;
    }

    public void setIsSupportedFAQ(String isSupportedFAQ) {
        this.isSupportedFAQ = isSupportedFAQ;
    }

    public String getIsSupportedBannerAD() {
        return isSupportedBannerAD;
    }

    public void setIsSupportedBannerAD(String isSupportedBannerAD) {
        this.isSupportedBannerAD = isSupportedBannerAD;
    }

    public String getIsSupportedOpenIDMod() {
        return isSupportedOpenIDMod;
    }

    public void setIsSupportedOpenIDMod(String isSupportedOpenIDMod) {
        this.isSupportedOpenIDMod = isSupportedOpenIDMod;
    }

    public String getIsSupportedShoppingMod() {
        return isSupportedShoppingMod;
    }

    public void setIsSupportedShoppingMod(String isSupportedShoppingMod) {
        this.isSupportedShoppingMod = isSupportedShoppingMod;
    }

    public String getIsSupportedEPF() {
        return isSupportedEPF;
    }

    public void setIsSupportedEPF(String isSupportedEPF) {
        this.isSupportedEPF = isSupportedEPF;
    }

    public String getEpfName() {
        return epfName;
    }

    public void setEpfName(String epfName) {
        this.epfName = epfName;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getLanguageList() {
        return languageList;
    }

    public void setLanguageList(String languageList) {
        this.languageList = languageList;
    }
}
