package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactGroupTestDelete extends TestBase{

    private int idOfSelectedGroup;
    @BeforeTest
    public void checkContacts() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Ivan").withLastName("Ivanov").
                    withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").withMobilePhone("+78521457474"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }

        Groups groups = app.db().groups();
        Contacts contact = app.db().contacts();
        ContactData contactToDelete = contact.iterator().next();
        GroupData groupToDelete = groups.iterator().next();
        idOfSelectedGroup = groupToDelete.getId();

        Contacts contactsInGroup = app.db().getContactsInGroup(idOfSelectedGroup);
        if (contactsInGroup.size() == 0) {
            app.goTo().homePage();
            app.contact().addContactToGroup(contactToDelete, groupToDelete);
        }
    }

    @Test
    public void testContactGroup() {
        Contacts before = app.db().getContactsInGroup(idOfSelectedGroup);
        Contacts contact = app.db().contacts();
        ContactData contactToDelete = contact.iterator().next();

        app.goTo().homePage();
        app.contact().selectGroup(idOfSelectedGroup);
        app.contact().deleteContactFromGroup(contactToDelete);
        Contacts after = app.db().getContactsInGroup(idOfSelectedGroup);

        assertThat(before, equalTo(after.withAdded(contactToDelete)));
    }
}
