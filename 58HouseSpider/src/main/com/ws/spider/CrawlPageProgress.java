package com.ws.spider;

import com.ws.domain.ConnectToDomain;
import com.ws.domain.HouseDTO;
import com.ws.log.CrawLog;
import com.ws.property.PropertySeting;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by laowang on 17-3-4.
 */
public class CrawlPageProgress implements PageProcessor {

    private Site site = Site.me().setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
            .setCharset("utf-8")
            .setCycleRetryTimes(3)
            .setRetrySleepTime(3)
            .setSleepTime(1000)
            .addCookie("id58","c5/ns1i6aSMB6/TrDJEJAg==")
            .addCookie("als","0")
            .addCookie("ipcity","sh%7C%u4E0A%u6D77%7C0")
            .addCookie("commonTopbar_myfeet_tooltip","end")
            .addCookie("58tj_uuid","6f41a3f2-ee22-42d1-a245-c129bd589549")
            .addCookie("new_session","0")
            .addCookie("new_uv","5")
            .addCookie("utm_source","")
            .addCookie("spm","")
            .addCookie("init_refer","http%253A%252F%252Fcallback.58.com%252Ffirewall%252Fvalid%252F1709236824.do%253Fnamespace%253Dchuzulistphp%2526url%253Dsh.58.com%25252Fchuzu%25252F0%25252Fpn10%25252F")
            .addCookie("commontopbar_city","2%7C%u4E0A%u6D77%7Csh")
            .addCookie("twe","2")
            .addCookie("path","/")
            .addCookie("domain",".anjuke.com")
            .addCookie("aQQ_ajkguid","1D2AE2E0-4F11-90B3-175E-2418E51AF235")
            .addCookie("expires","Mon")
            .addCookie("Max-Age","31622400");

    private ConnectToDomain connectToDomain = new ConnectToDomain();

    private int personal = 0;

    public void process(Page page) {
        int size = 0;
        String url = page.getUrl().toString();
        Properties properties = PropertySeting.getProperties(new CrawlPageProgress());
        if(url.contains("http://sh.58.com/"))
            size = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li").all().size();
        if(url.contains(properties.getProperty("58_house_personal_url")))
            personal = 1;
        if(url.contains(properties.getProperty("anjuke_house_personal_url"))){
            personal = 1;
            size = page.getHtml().xpath("//*[@id=\"list-content\"]/div").all().size();
        }

        System.out.println(size);
        System.out.println(page.getUrl());
        if(url.contains("http://sh.58.com/"))
            for(int i=1;i<size;i++){
                String houseMessage = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li["+i+"]/div[2]/h2/a/text()").get().trim();
                String houseLink = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li["+i+"]/div[2]/h2/a/@href").get().trim();
                String houseType = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li["+i+"]/div[2]/p[1]/text()").get().trim();
                String houseLocation = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li["+i+"]/div[2]/p[2]/text()").get().trim();
                String housePrice = page.getHtml().xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li["+i+"]/div[3]/div[2]/b/text()").get().trim();
                insertToDTO(houseMessage,houseLink,houseType,houseLocation,housePrice,personal);
            }
        else
            for(int i = 3; i <= size;i++){
                String houseMessage = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[1]/h3/a/text()").get();
                if(houseMessage == null)
                    houseMessage = "";
                else
                    houseMessage = houseMessage.trim();
                String houseLink = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[1]/h3/a/@href").get();
                if(houseLink == null)
                    houseLink = "";
                else
                    houseLink = houseLink.trim();
                String houseType = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[1]/p[1]/text()").get();
                if(houseType == null)
                    houseType = "";
                else
                    houseType = houseType.trim();
                String houseLocation1 = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[1]/address/a/text()").get();
                String houseLocation2 = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[1]/address/text()").get();
                if(houseLocation1 == null)
                    houseLocation1 = "";
                if(houseLocation2 == null)
                    houseLocation2 = "";
                String houseLocation = houseLocation1 + houseLocation2;
                String housePrice = page.getHtml().xpath("//*[@id=\"list-content\"]/div["+i+"]/div[2]/p/strong/text()").get();
                if(housePrice == null)
                    housePrice = "";
                else
                    housePrice = housePrice.trim();
                if(!houseMessage.equals(""))
                    insertToDTO(houseMessage,houseLink,houseType,houseLocation,housePrice,personal);
            }

    }

    public Site getSite() {
        return site;
    }

    private void insertToDTO(String houseMessage, String houseLink, String houseType, String houseLocation, String housePrice, int personal){
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setHouseMessage(houseMessage);
        System.out.println("房屋标题："+houseMessage);
        houseDTO.setHouseLink(houseLink);
        System.out.println("房屋链接："+houseLink);
        houseDTO.setHouseType(houseType);
        System.out.println("房屋类型："+houseType);
        houseDTO.setHouseLocation(houseLocation);
        System.out.println("房屋地址："+houseLocation);
        houseDTO.setHousePrice(housePrice);
        System.out.println("房屋价格："+housePrice);
        houseDTO.setHousePersonal(personal);
        try {
            connectToDomain.insert(houseDTO);
        } catch (SQLException e) {
            CrawLog.getLogger().error("get page message error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
    }
}
