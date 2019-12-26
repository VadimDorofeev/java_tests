package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

public class ChangeHelper extends HelperBase {
    public ChangeHelper(ApplicationManager app) {
        super(app);
    }

    public User choseUser(int id) {
        driver.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
        driver.findElement(By.partialLinkText("Manage")).click();
        driver.findElement(By.partialLinkText("Manage Users")).click();
        driver.findElement(By.xpath("//a[contains(@href='manage_user_edit_page.user_id=" + id + "')]/@href"));
        String username = driver.findElement(By.name("username")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        User user = new User(username, email);
        return user;
    }

    public void resetPassword() {
        driver.findElement(By.xpath("//input[@value='Reset Password']")).click();
    }

    public void changePassword(String link, String password) {
        driver.get(link);
        type(By.name("password"),password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
