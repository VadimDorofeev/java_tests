package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getPhone());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initCreationNewContact() {
        click(By.linkText("add new"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitNewContact() {
        click(By.name("submit"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void initModificationContact(int index) {
        driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

    public void selectContact(int index) {
        driver.findElements(By.xpath("//input[@type='checkbox']")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitDeletion() {
        driver.switchTo().alert().accept();
    }

    public void createContact(ContactData contactData, boolean b) {
        initCreationNewContact();
        fillContactForm(contactData, true);
        submitNewContact();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> lines = element.findElements(By.tagName("td"));
            String firstName = lines.get(2).getText();
            String lastName = lines.get(1).getText();
            String phone = lines.get(5).getText();
            String email = lines.get(4).getText();
            String address = lines.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstName, lastName, address, email, phone,
                    null);
            contacts.add(contact);
        }
        return contacts;
    }
}
