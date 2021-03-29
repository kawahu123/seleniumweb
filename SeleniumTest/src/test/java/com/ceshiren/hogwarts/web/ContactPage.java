package com.ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
    //parterInfo定位符
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
        sendKeys(By.id("memberSearchInput"),departName);
        System.out.println("******************");
        String content=driver.findElement(parterInfo).getText();
        System.out.println(content);
        //确保目标对象已经加载完毕
//        click(By.cssSelector(".ww_icon_AddMember"));
        System.out.println("******************");
        return this;
    }


    public String getPartyInfo(){
        String content=driver.findElement(parterInfo).getText();
        System.out.println(content);
        return content;
    }

    public ContactPage addDepart(String departName) {
//      todo:添加部门

//        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),departName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("定向班四期")).get(0).click();
//        click(By.xpath("(//a[text()='霍格沃兹学院'])[2]"));
        click(By.linkText("确定"));
        return this;
    }

    public void  clearAlldeparts() throws InterruptedException {
        //点击搜索，并查找"定向班四期"
        //searchDepart("定向班四期");
        //点击搜索
        click(By.id("memberSearchInput"));
        sendKeys(By.id("memberSearchInput"),"定向班四期");
        //先判断“定向班四期”部门下是否有学员: a1有则先删除所有的成员,a2再删它的子部门；
        //没有成员则，判断是否有它的子部门: b1有删除子部门，b2无则不删除
        String text_Has_Member = driver.findElements(By.cssSelector(".js_has_member")).get(0).getText();
        if(text_Has_Member.contains("姓名") || text_Has_Member.contains("职务") || text_Has_Member.contains("部门") || text_Has_Member.contains("手机") || text_Has_Member.contains("邮箱") ){
            //a1如果有成员先删除成员，
            clicks(By.cssSelector(".ww_checkbox"),0);
            clicks(By.cssSelector(".js_delete"),0);
            click(By.linkText("确认"));
            click(By.id("clearMemberSearchInput"));
            System.out.println("删除成员成功");
            //a2再删它的子部门
            /*if(){

            }*/
        }else {
            System.out.println("无成员，不用删除");
            click(By.id("clearMemberSearchInput"));
        }

    }

}
