package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeTest
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Ivan").withLastName("Ivanov").
                    withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").
                    withMobilePhone("+78521457474"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Ivan").withLastName("Ivanov").
                withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").
                withMobilePhone("+78521457474").withGroup("test1");
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withoutAdded(modifiedContact).withAdded(contact)));
    }
}
