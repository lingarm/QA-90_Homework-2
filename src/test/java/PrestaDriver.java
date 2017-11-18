import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PrestaDriver {

    WebDriver driver = null;

    PrestaDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public void logIn() {
        this.driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement login_field = this.driver.findElement(By.id("email"));
        login_field.sendKeys("webinar.test@gmail.com");

        WebElement pass_field = this.driver.findElement(By.id("passwd"));
        pass_field.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement button = this.driver.findElement(By.name("submitLogin"));
        button.click();

        this.sleep();
    }

    public void logOut() {
        WebElement pic = this.driver.findElement(By.className("imgm"));
        pic.click();

        WebElement exit = this.driver.findElement(By.id("header_logout"));
        exit.click();
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void checkMenu() {
        String tagTitle = "page-title";
        String tagMenuUl = "menu";
        String tagMenuLi = "maintab";
        List<WebElement> menu = this.driver.findElement(By.className(tagMenuUl)).findElements(By.className(tagMenuLi));
        int size = menu.size();
        try {
            int i = 0;
            do{
                if(i == 2 || i == 6) {
                    tagTitle = "title";
                    tagMenuUl = "main-menu";
                    tagMenuLi = "link-levelone";
                }
                else {
                    tagTitle = "page-title";
                    tagMenuUl = "menu";
                    tagMenuLi = "maintab";
                }
                menu.get(i).click();
                System.out.println("Переход в раздел " + i);
                String titleBefore = this.driver.findElement(By.className(tagTitle)).getText();
                System.out.println("Заголовок раздела: " + titleBefore);
                this.driver.navigate().refresh();
                this.sleep();
                menu = this.driver.findElement(By.className(tagMenuUl)).findElements(By.className(tagMenuLi));
                String titleAfter = this.driver.findElement(By.className(tagTitle)).getText();
                if(titleBefore.equals(titleAfter)) {
                    System.out.println("Пользователь остался в том же разделе после перезагрузки страницы");
                }
                else {
                    System.out.println("Пользователь покинул раздел после перезагрузки страницы");
                }
                i++;
            }while(i < size);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void closeBrowser() {
        driver.quit();
    }
}