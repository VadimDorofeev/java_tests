package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().goToHomePage();
        app.getContactHelper().initModificationContact();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", "Lenin prospect, 34", "petr.petrov@mail.ru", "+78521457474"));
        app.getContactHelper().submitModificationContact();
        app.getContactHelper().returnToHomePage();
    }
}
