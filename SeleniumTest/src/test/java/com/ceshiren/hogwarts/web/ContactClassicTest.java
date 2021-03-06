package com.ceshiren.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import org.omg.CORBA.Object;
//import org.omg.CORBA.Object;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Panghu
 * @Title:
 * @package:
 * @Description:
 * @date Created in 1:53 2021-03-16
 */
public class ContactClassicTest {

    private static WebDriver driver;

    static void needLogin() throws IOException, InterruptedException {
        //扫码登录
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
        System.exit(0);
    }

    @BeforeAll
    static void beforAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if(file.exists()){
            //利用cookie复用session登录
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String,Object>>>() {};

            List<HashMap<String,Object>> cookies = mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap->{
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
            });

            driver.navigate().refresh();
        }else {
            needLogin();
        }


    }

/*
    @Test
    void contackAdd(){
        driver.findElement(By.cssSelector(".ww_indexImg_AddMember")).click();
        //driver.findElement(By.linkText("添加成员")).click();
        driver.findElement(By.name("username")).sendKeys("kawahu20210323");
        driver.findElement(By.name("acctid")).sendKeys("kawahu20210323");
        driver.findElement(By.name("mobile")).sendKeys("13820210323");
        driver.findElement(By.linkText("保存")).click();
    }
*/

    @Test
    void contackAdd(){
        click(By.cssSelector(".ww_indexImg_AddMember"));
        //driver.findElement(By.linkText("添加成员")).click();
        sendKeys(By.name("username"),"kawahu20210323");
        sendKeys(By.name("acctid"),"kawahu20210323");
        sendKeys(By.name("mobile"),"13820210323");
        click(By.linkText("保存"));
    }

    @Test
    void search(){

    }

    @Test
    void departmentSearch(){
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"),"销售部");
        String content=driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        //确保目标对象已经加载完毕
        click(By.cssSelector(".ww_icon_AddMember"));
        content=driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        assertTrue(content.contains("无任何成员"));
    }


    void click(By by){
        driver.findElement(by).click();
    }

    void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }

}
