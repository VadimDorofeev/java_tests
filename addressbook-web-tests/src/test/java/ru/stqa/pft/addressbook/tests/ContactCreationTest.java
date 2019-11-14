package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initCreationNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "Pushkin st, 54", "ivan.ivanov@mail.ru", "+78521457474"));
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToHomePage();
    }
}