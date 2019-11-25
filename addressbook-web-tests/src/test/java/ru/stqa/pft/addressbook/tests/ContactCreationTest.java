package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ivan", "Ivanov", "Pushkin st, 54",
                "ivan.ivanov@mail.ru", "+78521457474", "test1");
        app.getContactHelper().initCreationNewContact();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertTrue(after.size() == before.size() + 1);

        for (int i = 0; i < before.size(); i++) {
            System.out.println(before.get(i));
        }
        System.out.println("==========================================");
        for (int i = 0; i < after.size(); i++) {
            System.out.println(after.get(i));
        }

        contact.setId(after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}