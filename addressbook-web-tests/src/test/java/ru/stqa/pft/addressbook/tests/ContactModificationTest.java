package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Pushkin st, 54",
                    "ivan.ivanov@mail.ru", "+78521457474", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Petr", "Petrov", "Lenin prospect, 34",
                "petr.petrov@mail.ru", "+78521457474", null);
        app.getContactHelper().initModificationContact(before.size() - 1);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitModificationContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertTrue(before.size() == after.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
