package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

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
        ContactData contactForAdding = null;
        GroupData groupForAdding = null;
        Contacts before= null;

        for (GroupData group : groups) {
            Contacts contactInGroup = app.db().getContactsInGroup(group.getId());
            for (ContactData contact : contactInGroup) {
                if (!contactInGroup.contains(group)) {
                    before = contactInGroup;
                    contactForAdding = contact;
                    groupForAdding = group;
                    return;
                }
            }
        }

        if (contactForAdding == null) {
            System.out.println("null");
        } else System.out.println(contactForAdding);
        if (groupForAdding == null) {
            System.out.println("null");
        } else System.out.println(groupForAdding);

//        app.goTo().homePage();
//        Groups groups = app.db().groups();
//        Contacts before = app.db().contacts();
//        ContactData newContact = new ContactData().withFirstName("Ivan").withLastName("Ivanov").inGroup(groups.iterator().next());
//        app.contact().createWithGroup(newContact);
//        Contacts after = app.db().contacts();
//        assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream()
//                .mapToInt((c) -> c.getId()).max().getAsInt()))));
//        verifyContactListInUI();
    }

}
