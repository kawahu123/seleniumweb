package com.ceshiren.hogwarts.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Panghu
 * @Title:
 * @package:
 * @Description:
 * @date Created in 1:39 2021-03-24
 */
public class ContactPOTest {

    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面
        //复用session登录
        MainPage mainPage = new MainPage();
        //跳转页面
        //部门搜索
        ContactPage contactPage = mainPage.contact();
        //addMember("kawahu20210323", "kawahu20210323", "15811154678");
        contactPage.searchDepart("销售部");
        String content=contactPage.getPartyInfo();
        assertTrue(content.contains("无任何成员"));
   //     assert contactPage.search("kawahu20210323").getInfo();
    }

    @Test
    void testDepartSearchChain() throws IOException, InterruptedException {
        assertTrue(new MainPage().contact().searchDepart("销售部").getPartyInfo().contains("无任何成员"));
    }
}