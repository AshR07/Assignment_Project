package com.takehome.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestScript {
    public static void main(String[] args) {

        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            //  WebDriverManager.getInstance(CHROME).setup();
            WebDriver driver = new ChromeDriver();
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            driver.navigate().to("http://slashdot.org/");

            //Get Article Count
            List<WebElement> articles = driver.findElements(By.xpath("//h2[@class='story']"));
            System.out.println("Article Count  ::" + articles.size());

            //Get Unique Icons
            List<WebElement> uniqueIcons = driver.findElements(By.xpath("//span[@class='topic']/a/img"));
            System.out.println("uniqueIcons Count  ::" + uniqueIcons.size());

            // now submit poll
            WebElement polling = driver.findElement(By.id("poll-title"));
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", polling);
            driver.findElement(By.xpath("//input[@type='radio'and @value='1']")).click();
            driver.findElement(By.xpath("//button[@class='btn-polls']")).click();
           Thread.sleep(10000);


            WebElement pollingView = driver.findElement(By.xpath("//*[@class = 'pollBooth_view']"));
           // Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pollingView);
           // driver.findElement(By.id("input_name")).getAttribute("value");
            Thread.sleep(200);

            //Number of perople voted for same
            String pollResults = driver.findElement(By.xpath("//html/body/section[1]/div[4]/div/div/div/div[1]/div/div[1]/div[2]/div/div")).getText();
            removeTillWord(pollResults, "votes");

            System.out.println("pollResults ::"+removeTillWord(pollResults, "votes"));

            driver.quit();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String removeTillWord(String input, String word) {
        return input.substring(0, input.indexOf(word));
    }



}


