package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTest extends TestBase {

    @BeforeTest
    public void checkContacts() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Ivan").withLastName("Ivanov").
                    withAddress("Pushkin st, 54").withEmail("ivan.ivanov@mail.ru").
                    withMobilePhone("+78521457474"));
        }
    }

    public void checkGroups() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testContactGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData modifiedContact = null;
        GroupData groupForAdding = null;
        Contacts before = null;

        for (GroupData group : groups) {
            Contacts contactsInGroups = app.db().getContactsInGroup(group.getId());
            if (contactsInGroups.size() != 0) {
                groupForAdding = group;
                modifiedContact = contactsInGroups.iterator().next();
                before = contactsInGroups;
            } else {
                for (ContactData contact : contacts) {
                    if (!contactsInGroups.contains(contact)) {
                        groupForAdding = group;
                        modifiedContact = contact;
                        before = contactsInGroups;
                        break;
                    }
                }
            }
        }

        if (groupForAdding == null) {
            groupForAdding = new GroupData().withName("new group NULL");
            app.goTo().groupPage();
            app.group().create(groupForAdding);
            before = app.db().getContactsInGroup(groupForAdding.getId());
            modifiedContact = app.db().contacts().iterator().next();
            app.contact().addContactToGroup(modifiedContact, groupForAdding);
        }

        app.goTo().homePage();
        app.contact().addContactToGroup(modifiedContact, groupForAdding);
        Contacts after = app.db().getContactsInGroup(groupForAdding.getId());

        System.out.println("before.size()= " + before.size() + ", after.size()=" + after.size());

        Assert.assertThat(after, equalTo(before.withAdded(modifiedContact)));

    }
}
