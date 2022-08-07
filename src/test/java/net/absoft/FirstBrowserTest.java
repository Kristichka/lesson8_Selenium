package net.absoft;

import static org.testng.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FirstBrowserTest{

 private WebDriver driver;

 @BeforeSuite
 public void downloadDriver(){
     WebDriverManager.chromedriver().setup();
}

  @BeforeMethod
  public void setUp(){
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khristina\\chromedriver.exe");
      driver = new ChromeDriver();
        }

    @Test
    public void firstBrowserTest(){
        driver.get("https://www.google.com");
        assertEquals(driver.getTitle(), "Google", "Unexpected Google page title" );
    }

    @AfterMethod
    public void tearDown(){
      driver.quit();
    }
}
