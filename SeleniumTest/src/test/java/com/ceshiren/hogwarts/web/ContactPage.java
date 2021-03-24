package com.ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Panghu
 * @Title:
 * @package:
 * @Description:
 * @date Created in 1:22 2021-03-24
 */
public class ContactPage extends BasePage{
    //PO原则2，不要暴露页面内部实现
    private By parterInfo=By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例中
        super(driver);
    }

    //PO原则6 添加成功与添加失败返回的页面是不同的，需要封装不同的方法
    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }

    //PO原则6 添加成功与添加失败返回的页面是不同的，需要封装不同的方法
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }

    //PO原则5 不要实现多有的方法，按需实现
    public ContactPage searchDepart(String departName){
        //用公共方法代表页面所提供的功能，PO原则1
        //PO原则3，通常不要在PO内加断言
        sendKeys(By.id("memberSearchInput"),"销售部");
        String content=driver.findElement(parterInfo).getText();
        System.out.println(content);
        //确保目标对象已经加载完毕
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }


    public String getPartyInfo(){
        String content=driver.findElement(parterInfo).getText();
        System.out.println(content);
        return content;
    }

}
