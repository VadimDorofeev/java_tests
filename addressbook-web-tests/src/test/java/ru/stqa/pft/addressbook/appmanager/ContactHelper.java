package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        click(By.linkText("home"));
    }

    public void submitNewContact() {
        click(By.name("submit"));
    }

    public void createContact(ContactData contactData, boolean b) {
        initCreationNewContact();
        fillContactForm(contactData, true);
        submitNewContact();
        contactCache = null;
        returnToHomePage();
    }

    public void create(ContactData contact) {
        initCreationNewContact();
        fillContactForm(contact, true);
        submitNewContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        submitDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModificationContactById(contact.getId());
        fillContactForm(contact, false);
        submitModificationContact();
        contactCache = null;
        returnToHomePage();
    }


    public void initModificationContactById(int id) {
        driver.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

    public void selectContact(int index) {
        driver.findElements(By.xpath("//input[@type='checkbox']")).get(index).click();
    }

    public void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[id='"+ id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitDeletion() {
        driver.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public Set<ContactData> list() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> lines = element.findElements(By.tagName("td"));
            String firstName = lines.get(2).getText();
            String lastName = lines.get(1).getText();
            String phone = lines.get(5).getText();
            String email = lines.get(4).getText();
            String address = lines.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
                    withAddress(address).withEmail(email).withPhone(phone));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts (contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> lines = element.findElements(By.tagName("td"));
            String firstName = lines.get(2).getText();
            String lastName = lines.get(1).getText();
            String phone = lines.get(5).getText();
            String email = lines.get(4).getText();
            String address = lines.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
                    withAddress(address).withEmail(email).withPhone(phone));
        }
        return new Contacts(contactCache);
    }

}
