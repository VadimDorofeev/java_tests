package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("Ivan").withLastName("Ivanov").
                    withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").
                    withPhone("+78521457474").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        int index = before.size() - 1;
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertTrue(after.size() == before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);

    }


}
