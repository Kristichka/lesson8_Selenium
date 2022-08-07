package net.absoft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SWAGLabsBrowersTest {

    private WebDriver driver;

    @BeforeSuite
    public void downloadDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khristina\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

//    @Test
//    public void testSuccessfulAuthorisation(){
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//
//        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
//        assertFalse(driver.findElements(By.id("shopping_cart_container")).isEmpty(), "User is not logged in, shopping cart icon not found");
//    }

//        Перевірте, що standard_user не може увійти в систему з неправильним паролем і отримує відповідне повідомлення про помилку.
    @Test
    public void TestWrongPassword(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        assertEquals(driver.findElement(By.xpath("//div[contains(@class,'error-message')]")).getText(), "Epic sadface: Username and password do not match any user in this service",
                "Error message do not match");

        }

        //    Перевірте, що locked_out_user не може увійти в систему, бо його аккаунт заблокованний і отримує відповідне повідомлення про помилку.
    @Test
    public void TestLockedUser(){
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        assertEquals(driver.findElement(By.xpath("//div[contains(@class,'error-message')]")).getText(), "Epic sadface: Sorry, this user has been locked out.",
                "Error message do not match");
    }

      @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

