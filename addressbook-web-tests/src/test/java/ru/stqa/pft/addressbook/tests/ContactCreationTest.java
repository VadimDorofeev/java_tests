package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initCreationNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "Pushkin st, 54",
                "ivan.ivanov@mail.ru", "+78521457474", "test1"), true);
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertTrue(after == before + 1);
    }
}