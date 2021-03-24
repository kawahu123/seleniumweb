package com.ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Panghu
 * @Title:
 * @package:
 * @Description:
 * @date Created in 0:22 2021-03-25
 */
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public BasePage() {
    }

    void click(By by){
        driver.findElement(by).click();
    }

    void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }
}
