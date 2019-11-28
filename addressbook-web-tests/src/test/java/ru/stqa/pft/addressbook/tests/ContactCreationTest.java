package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstName("Ivan").withLastName("Ivanov").
                withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").
                withPhone("+78521457474").withGroup("test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertTrue(after.size() == before.size() + 1);

        for (int i = 0; i < before.size(); i++) {
            System.out.println(before.get(i));
        }
        System.out.println("==========================================");
        for (int i = 0; i < after.size(); i++) {
            System.out.println(after.get(i));
        }

        contact.withId(after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}