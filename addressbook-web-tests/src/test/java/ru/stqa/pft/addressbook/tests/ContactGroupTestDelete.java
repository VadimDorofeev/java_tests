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

public class ContactGroupTestDelete {

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
        Contacts contacts = app.db().contacts();
        GroupData groupToDelete = groups.iterator().next();
        ContactData contactToAdd = contacts.iterator().next();
        Contacts contactsInGroup = app.db().getContactsInGroup(groupToDelete.getId());

        if (contactsInGroup.size() == 0) {
            app.contact().addContactToGroup(contactToAdd, groupToDelete);
        }

    }

    @Test
    public void testContactGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData modifiedContact = contacts.iterator().next();
        for (GroupData group : groups) {
            Contacts contactInGroup = app.db().getContactsInGroup(group.getId());
        }

        GroupData groupForAdding = groups.iterator().next();

        Contacts before = app.db().getContactsInGroup(groupForAdding.getId());
        app.contact().deleteFromGroup(modifiedContact, groupForAdding);
        Contacts after = app.db().getContactsInGroup(groupForAdding.getId());

        assertThat(before, equalTo(after.withoutAdded(modifiedContact)));
    }
}
